package com.ppdream.xweb.controller;

import com.ppdream.xweb.dto.TestParam;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: x43125
 * @Date: 22/01/02
 */
@RequestMapping("/test")
@RestController
public class HelloController {

    @GetMapping("/sayHello")
    public String sayHello() {
        return "hello world";
    }

    @PostMapping("/testParam")
    public String testParam(String key1, String key2) {
        return key1 + key2;
    }

    @PostMapping("/testBody")
    public String testBody(@RequestBody TestParam param) {
        return param.getParam1() + param.getParam2();
    }

    @GetMapping("/testGet")
    public String testGet() {
        return "test get success";
    }
}
