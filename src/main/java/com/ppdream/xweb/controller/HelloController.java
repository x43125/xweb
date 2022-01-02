package com.ppdream.xweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: x43125
 * @Date: 22/01/02
 */
@RequestMapping("/test")
@RestController
public class HelloController {

    @GetMapping("/helloworld")
    public String sayHello() {
        return "hello world";
    }

}
