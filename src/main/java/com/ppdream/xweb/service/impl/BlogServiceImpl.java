package com.ppdream.xweb.service.impl;

import cn.hutool.core.bean.BeanUtil;
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
import com.ppdream.xweb.utils.redis.BlogProtostuffSerializer;
import com.ppdream.xweb.utils.redis.ProtostuffSerializer;
import com.ppdream.xweb.vo.BlogVO;
import io.lettuce.core.RedisBusyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.SQLDataException;
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


    private static final String BLOG_PREFIX = "ppdream:blog";
    private static final String LIKE_PREFIX = "ppdream:blog:like:";

    @Override
    public Long incrLike(String blogName) {
        LOGGER.info("点赞+1");
        String key = LIKE_PREFIX + blogName;

        if (redisTemplate == null) {
            throw new RedisBusyException("Redis连接创建失败");
        }

        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            LOGGER.info("redis命中，点赞+1");
            return redisTemplate.opsForValue().increment(key);
        } else {
            LOGGER.warn("redis未命中，到数据库中查询并存入redis中");
            Blog blog = blogMapper.selectByBlogName(blogName);
            if (ObjectUtil.isNotEmpty(blog)) {
                return redisTemplate.opsForValue().increment(key, blog.getLikeCount() + 1);
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
        System.out.println(((List<Blog>) data.get("list")).get(0).getName());
        return CommonResult.success(data);
    }

    @Value("${upload.blogDir}/")
    private String blogDir;

    @Override
    public Blog readBlog(String blogName) throws SQLDataException {
        LOGGER.info("读取博客信息: " + blogName);
        if (redisTemplate == null) {
            throw new RedisBusyException("Redis连接创建失败");
        }

        BlogProtostuffSerializer serializer = new BlogProtostuffSerializer();

        Blog blog = null;
        // 有redis走redis
        Object blogStr = redisTemplate.opsForHash().get(BLOG_PREFIX, blogName);
        if (ObjectUtil.isNotEmpty(blogStr)) {
            LOGGER.info("redis命中");
            // todo 有问题
            blog = serializer.deserialize(blogStr.toString());
        } else {
            LOGGER.warn("redis未命中，读数据库");
            // redis未命中则直接去读库，并将值加到redis中
            blog = blogMapper.selectByBlogName(blogName);
            if (ObjectUtil.isEmpty(blog)) {
                throw new SQLDataException("数据库无该博客: " + blogName);
            }
        }

        String localBlogDir = blogDir + blog.getName() + "." + blog.getType();
        LOGGER.info("读取文件:" + localBlogDir);
        String blogContent;
        try {
            blogContent = FileUtils.readFile2Str(localBlogDir);
        } catch (Exception e) {
            LOGGER.error("读取博客内容失败");
            throw new FileException(FileExceptionCodes.FILE_READ_EXCEPTION.getCode(), null);
        }

        // 数据库缓存一致性处理方式：先更新数据库中数据，再更新redis中数据
        blog.setReadCount(blog.getReadCount() + 1);
        blogMapper.updateByPrimaryKey(blog);

        String serialize = serializer.serialize(blog);

        redisTemplate.opsForHash().put(BLOG_PREFIX, blogName, serialize);

        BlogVO blogVO = new BlogVO();
        blogVO.setContent(blogContent);
        BeanUtil.copyProperties(blog, blogVO);
        return blogVO;
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




