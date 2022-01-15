package com.ppdream.xweb.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ppdream.xweb.common.api.CommonResult;
import com.ppdream.xweb.common.exception.ServiceException;
import com.ppdream.xweb.common.exception.file.FileException;
import com.ppdream.xweb.common.exception.file.FileExceptionCodes;
import com.ppdream.xweb.dto.BlogDto;
import com.ppdream.xweb.dto.KeywordDto;
import com.ppdream.xweb.entity.Blog;
import com.ppdream.xweb.mapper.BlogMapper;
import com.ppdream.xweb.service.BlogService;
import com.ppdream.xweb.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public CommonResult<Map<String, Object>> getBlogList(KeywordDto keywordDto) {
        IPage<Blog> blogPage = blogMapper.selectAll(new Page<Blog>(keywordDto.getCurrent(), keywordDto.getSize()));
        List<Blog> blogList = blogPage.getRecords();
        Map<String, Object> data = new HashMap<>();
        data.put("list", blogList);
        data.put("size", blogPage.getSize());
        data.put("current", blogPage.getCurrent());
        data.put("total", blogPage.getTotal());
        System.out.println(((List<Blog>)data.get("list")).get(0).getName());
        return CommonResult.success(data);
    }

    @Value("${upload.blogDir}/")
    private String blogDir;

    @Override
    public List<String> readBlog(String blogName) {
        Blog blog = blogMapper.selectByBlogName(blogName);

        String localBlogDir = blogDir + blog.getName() + "." + blog.getType();
        LOGGER.info("读取文件:" + localBlogDir);
        List<String> blogContent = null;
        try {
            blogContent = FileUtils.readFile(localBlogDir);
        } catch (Exception e) {
            LOGGER.error("读取博客内容失败");
            throw new FileException(FileExceptionCodes.FILE_READ_EXCEPTION.getCode(), null);
        }

        return blogContent;
    }

    @Override
    public boolean uploadBlog(BlogDto blogDto, MultipartFile file) {
        String uploadBlogDir = blogDir + blogDto.getName();
        File file1 = new File(uploadBlogDir);
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            throw new FileException(FileExceptionCodes.FILE_UPLOAD_FAILED.getCode(), null);
        }

        return file1.exists();
    }
}




