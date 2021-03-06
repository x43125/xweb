package com.ppdream.xweb.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ppdream.xweb.common.api.CommonResult;
import com.ppdream.xweb.common.exception.ServiceException;
import com.ppdream.xweb.common.exception.file.FileException;
import com.ppdream.xweb.common.exception.file.FileExceptionCodes;
import com.ppdream.xweb.dto.blog.BlogDto;
import com.ppdream.xweb.dto.KeywordDto;
import com.ppdream.xweb.dto.blog.BlogInteractDto;
import com.ppdream.xweb.entity.BlogLike;
import com.ppdream.xweb.entity.WebUser;
import com.ppdream.xweb.mapper.BlogLikeMapper;
import com.ppdream.xweb.entity.Blog;
import com.ppdream.xweb.mapper.BlogMapper;
import com.ppdream.xweb.mapper.WebUserMapper;
import com.ppdream.xweb.service.BlogService;
import com.ppdream.xweb.utils.FileUtils;
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
import java.util.Date;
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
    private BlogLikeMapper blogLikeMapper;
    @Autowired
    private WebUserMapper webUserMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private static final String BLOG_PREFIX = "ppdream:blog";

    @Override
    public Integer incrLike(BlogInteractDto blogInteractDto) throws SQLDataException {
        LOGGER.info("??????: " + blogInteractDto.getBlogName() + " ??????: " + blogInteractDto.getTourName());
        if (redisTemplate == null) {
            throw new RedisBusyException("Redis??????????????????");
        }

        Blog blog = (Blog) redisTemplate.opsForHash().get(BLOG_PREFIX, blogInteractDto.getBlogName());
        //todo ???????????????????????????????????????????????????
        if (ObjectUtil.isEmpty(blog)) {
            LOGGER.info("redis??????????????????mysql");
            blog = blogMapper.selectByBlogName(blogInteractDto.getBlogName());
            if (ObjectUtil.isEmpty(blog)) {
                LOGGER.error("??????????????????: " + blogInteractDto.getBlogName());
                throw new ServiceException("?????????????????????: " + blogInteractDto.getBlogName());
            }
        }

        blog.setLikeCount(blog.getLikeCount() + 1);

        WebUser tourUser = webUserMapper.selectByPrimaryKey(blogInteractDto.getTourId());
        if (ObjectUtil.isEmpty(tourUser)) {
            throw new SQLDataException("????????????: ??????id:" + blogInteractDto.getTourId() + " ?????????:" + blogInteractDto.getTourName());
        }

        // ??????bloglike??????????????????????????????
        BlogLike blogLike = new BlogLike();
        blogLike.setBlogId(blogInteractDto.getBlogId());
        blogLike.setBlogName(blogInteractDto.getBlogName());
        blogLike.setLikeUserId(blogInteractDto.getTourId());
        blogLike.setLikeUserName(blogInteractDto.getTourName());
        blogLike.setCreateTime(new Date());
        blogLike.setUpdateTime(new Date());
        blogLikeMapper.insert(blogLike);

        blogMapper.updateByPrimaryKey(blog);
        redisTemplate.opsForHash().put(BLOG_PREFIX, blogInteractDto.getBlogName(), blog);
        return blog.getLikeCount();
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
//        System.out.println(((List<Blog>) data.get("list")).get(0).getName());
        return CommonResult.success(data);
    }

    @Value("${upload.blogDir}/")
    private String blogDir;

    @Override
    public BlogVO readBlog(String blogName) throws SQLDataException {
        LOGGER.info("??????????????????: " + blogName);
        if (redisTemplate == null) {
            throw new RedisBusyException("Redis??????????????????");
        }

        Blog blog;
        // ???redis???redis
        Object blogStr = redisTemplate.opsForHash().get(BLOG_PREFIX, blogName);
        if (ObjectUtil.isNotEmpty(blogStr)) {
            LOGGER.info("redis??????");
            blog = (Blog) blogStr;
            LOGGER.info("redis: " + blog.getReadCount());
        } else {
            LOGGER.warn("redis????????????????????????");
            // redis?????????????????????????????????????????????redis???
            blog = blogMapper.selectByBlogName(blogName);
            if (ObjectUtil.isEmpty(blog)) {
                throw new SQLDataException("?????????????????????: " + blogName);
            }
            LOGGER.info("mysql:" + blog.getReadCount());
        }

        //todo redis??????????????????????????????????????????????????????redis????????????????????????????????????????????????????????????
        String localBlogDir = blogDir + blog.getName() + "." + blog.getType();
        LOGGER.info("????????????:" + localBlogDir);
        String blogContent;
        try {
            blogContent = FileUtils.readFile2Str(localBlogDir);
        } catch (Exception e) {
            LOGGER.error("????????????????????????");
            throw new FileException(FileExceptionCodes.FILE_READ_EXCEPTION.getCode(), null);
        }

        // ??????????????????????????????????????????????????????????????????????????????redis?????????
        blog.setReadCount(blog.getReadCount() + 1);
        blog.setUpdateTime(new Date());
        blogMapper.updateByPrimaryKey(blog);
        redisTemplate.opsForHash().put(BLOG_PREFIX, blogName, blog);

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

    @Override
    public Integer incrComment(BlogInteractDto blogInteractDto) {

        return 0;


    }
}




