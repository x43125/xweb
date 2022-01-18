package com.ppdream.xweb.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ppdream.xweb.common.api.CommonResult;
import com.ppdream.xweb.common.exception.request.RequestException;
import com.ppdream.xweb.dto.blog.BlogDto;
import com.ppdream.xweb.dto.KeywordDto;
import com.ppdream.xweb.dto.blog.BlogInteractDto;
import com.ppdream.xweb.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLDataException;

/** 博客
 * @Author: x43125
 * @Date: 22/01/02
 */
@RequestMapping("/blog")
@RestController
@CrossOrigin
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/getBlogList")
    private CommonResult getBlogList(@RequestBody KeywordDto keywordDto) {
        return blogService.getBlogList(keywordDto);
    }

    @PostMapping("/incrLike")
    public CommonResult incrLike(@RequestBody BlogInteractDto blogInteractDto) throws SQLDataException {
        return CommonResult.success(blogService.incrLike(blogInteractDto), "阅读量+1");
    }

    @PostMapping("/readBlog")
    public CommonResult readBlog(@RequestBody BlogDto blogDto) throws SQLDataException {
        if (ObjectUtil.isEmpty(blogDto) || StrUtil.isBlank(blogDto.getName())) {
            throw new RequestException("请求参数blogDto为空", null);
        }
        return CommonResult.success(blogService.readBlog(blogDto.getName()));
    }

    //todo
    @PostMapping("/uploadBlog")
    public CommonResult uploadBlog(@RequestParam("blogDto") BlogDto blogDto, @RequestParam("file") MultipartFile file) {
        if (ObjectUtil.isEmpty(blogDto)) {
            throw new RequestException("请求参数blogDto为空", null);
        }
        if (ObjectUtil.isEmpty(file)) {
            throw new RequestException("博客未上传", null);
        }
        return CommonResult.success(blogService.uploadBlog(blogDto, file));
    }
}
