package com.ppdream.xweb.controller;

import com.ppdream.xweb.common.api.CommonResult;
import com.ppdream.xweb.common.api.ResultCode;
import com.ppdream.xweb.dto.LoginUser;
import com.ppdream.xweb.dto.RegisterUser;
import com.ppdream.xweb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: x43125
 * @Date: 22/01/02
 */
@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public CommonResult login(@RequestBody LoginUser user) {
        LOGGER.info(user.toString());
        if (userService.login(user)) {
            LOGGER.info("登陆成功");
            return CommonResult.success("登陆成功");
        }
        LOGGER.error("登陆失败，用户名或密码错误");
        return CommonResult.failed(ResultCode.USER_PWD_FAILED);
    }

    @PostMapping("/register")
    public CommonResult register(@RequestBody RegisterUser user) {
        if (userService.register(user)) {
            LOGGER.info("注册成功");
            return CommonResult.success("注册成功");
        }
        return CommonResult.failed("注册失败");
    }

    @GetMapping("/getUserList")
    public CommonResult getUserList() {
        return CommonResult.success(userService.getUserList(), "获取用户列表");
    }
}
