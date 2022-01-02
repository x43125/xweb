package com.ppdream.xweb.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ppdream.xweb.common.exception.ServiceException;
import com.ppdream.xweb.entity.Blog;
import com.ppdream.xweb.mapper.BlogMapper;
import com.ppdream.xweb.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: x43125
 * @Date: 22/01/02
 */
@Service
public class BlogServiceImpl implements BlogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogServiceImpl.class);

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String LIKE_PREFIX = "ppdream:blog:like:";

    @Override
    public Long incrLike(String blogName) {
        LOGGER.info("点赞+1");
        String key = LIKE_PREFIX + blogName;

        if (redisTemplate.hasKey(key)) {
            LOGGER.info("redis命中，点赞+1");
            return redisTemplate.opsForValue().increment(key);
        } else {
            LOGGER.warn("redis未命中，到数据库中查询并存入redis中");
            Blog blog = blogMapper.selectByBlogName(blogName);
            if (ObjectUtil.isNotEmpty(blog)) {
                return redisTemplate.opsForValue().increment(key, blog.getLikeCount()+1);
            } else {
                throw new ServiceException("为查询到该博客");
            }
        }
    }
}
