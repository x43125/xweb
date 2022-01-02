package com.ppdream.xweb.controller;

import com.ppdream.xweb.common.api.CommonResult;
import com.ppdream.xweb.dto.LoginUser;
import com.ppdream.xweb.entity.User;
import com.ppdream.xweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: x43125
 * @Date: 22/01/02
 */
@RequestMapping("/account")
@RestController
public class AccountController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public CommonResult login(@RequestBody LoginUser user) {
        return CommonResult.success(userService.login(user), "登陆成功");
    }

    @PostMapping("/register")
    public CommonResult register(@RequestBody User user) {
        return CommonResult.success(userService.register(user), "注册成功");
    }

    @GetMapping("/getUserList")
    public CommonResult getUserList() {
        return CommonResult.success(userService.getUserList(), "获取用户列表");
    }
}
