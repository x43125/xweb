package com.ppdream.xweb.controller;

import com.ppdream.xweb.dto.TestBody;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: x43125
 * @Date: 22/01/02
 */
@RequestMapping("/test")
@RestController
@CrossOrigin
public class HelloController {

    @GetMapping("/sayHello")
    public String sayHello() {
        System.out.println("hello world");
        return "hello world";
    }

    @PostMapping("/testParam")
    public String testParam(String key1, String key2) {
        System.out.println(key1 + " " + key2);
        return key1 + key2;
    }

    @PostMapping("/testBody")
    public String testBody(@RequestBody TestBody body) {
        System.out.println(body.toString());
        return body.getKey1() + " " + body.getKey2();
    }

    @GetMapping("/testGet")
    public String testGet() {
        System.out.println("test get success");
        return "test get success";
    }
}
