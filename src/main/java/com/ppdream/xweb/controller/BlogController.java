package com.ppdream.xweb.controller;

import com.ppdream.xweb.common.api.CommonResult;
import com.ppdream.xweb.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 博客
 * @Author: x43125
 * @Date: 22/01/02
 */
@RequestMapping("/blog")
@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/incrLike")
    public CommonResult incrLike(String blogName) {
        return CommonResult.success(blogService.incrLike(blogName), "阅读量+1");
    }

}
