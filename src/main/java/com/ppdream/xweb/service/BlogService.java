package com.ppdream.xweb.service;

import com.ppdream.xweb.common.api.CommonResult;
import com.ppdream.xweb.dto.blog.BlogDto;
import com.ppdream.xweb.dto.KeywordDto;
import com.ppdream.xweb.dto.blog.BlogInteractDto;
import com.ppdream.xweb.entity.Blog;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLDataException;
import java.util.Map;

/**
 * @Author: x43125
 * @Date: 22/01/02
 */
public interface BlogService {
    /**
     * 点赞，点赞数加一
     * @param blogInteractDto
     * @return
     */
    Integer incrLike(BlogInteractDto blogInteractDto) throws SQLDataException;

    CommonResult<Map<String, Object>> getBlogList(KeywordDto keywordDto);

    /**
     * 读取博客内容，并将阅读量加一
     * @param blogName
     * @return
     * @throws SQLDataException
     */
    Blog readBlog(String blogName) throws SQLDataException;

    boolean uploadBlog(BlogDto blogDto, MultipartFile file);

    Integer incrComment(BlogInteractDto blogInteractDto);
}
