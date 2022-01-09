package com.ppdream.xweb.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ppdream.xweb.common.exception.ServiceException;
import com.ppdream.xweb.common.exception.user.UserPasswordNotMatchException;
import com.ppdream.xweb.dto.LoginUser;
import com.ppdream.xweb.dto.RegisterUser;
import com.ppdream.xweb.entity.User;
import com.ppdream.xweb.mapper.UserMapper;
import com.ppdream.xweb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: x43125
 * @Date: 22/01/02
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * redis: key:value
     *  ppdream:account:username:passwd
     */
    private static final String ACCOUNT_PREFIX = "ppdream:account:";

    private static final Long EXPIRE_TIME = 30L;
    @Override
    public boolean login(LoginUser user) {
        String key = ACCOUNT_PREFIX + user.getUsername();
        String value = redisTemplate.opsForValue().get(key);

//        ProtostuffSerializer serializer = new ProtostuffSerializer();

        if (StrUtil.isBlank(value)) {
            LOGGER.warn("redis 未命中，将到数据库中查找");
            LOGGER.info("用户登录中");
            User userFromDb = userMapper.selectByUsername(user.getUsername());
            if (ObjectUtil.isEmpty(userFromDb) || !userFromDb.getPasswd().equals(user.getPasswd())) {
                return false;
            } else {
                LOGGER.info("验证成功，登录");
                // 将用户信息放入redis中
                redisTemplate.opsForValue().setIfAbsent(key, user.getPasswd(), EXPIRE_TIME, TimeUnit.MINUTES);
//                redisTemplate.opsForValue().setIfAbsent(key, serializer.serialize(user), EXPIRE_TIME, TimeUnit.MINUTES);
                return true;
            }
        } else {
            LOGGER.info("redis 命中，验证账户信息");
//            User deserialize = serializer.deserialize(value);
//            if (deserialize.getPasswd().equals(user.getPasswd())) {
            if (value.equals(user.getPasswd())) {
                LOGGER.info("信息正确，登陆成功");
                // 更新账户登陆时间
                redisTemplate.expire(key, EXPIRE_TIME, TimeUnit.MINUTES);
                return true;
            } else {
                LOGGER.error("密码错误");
                return false;
            }
        }
    }

    @Override
    public boolean register(RegisterUser user) {
        LOGGER.info("注册用户...");
        User userFromDb = userMapper.selectByUsername(user.getUsername());
        if (ObjectUtil.isNotEmpty(userFromDb)) {
            throw new ServiceException("用户名已存在，请换一个");
        }

        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insert(user);
        LOGGER.info("注册成功");
        return true;
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectAll();
    }


}
