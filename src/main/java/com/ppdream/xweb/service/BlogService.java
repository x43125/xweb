package com.ppdream.xweb.service;

import com.ppdream.xweb.common.api.CommonResult;
import com.ppdream.xweb.dto.BlogDto;
import com.ppdream.xweb.dto.KeywordDto;
import com.ppdream.xweb.entity.Blog;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Author: x43125
 * @Date: 22/01/02
 */
public interface BlogService {

    Long incrLike(String blogName);

    CommonResult<Map<String, Object>> getBlogList(KeywordDto keywordDto);

    List<String> readBlog(String blogName);

    boolean uploadBlog(BlogDto blogDto, MultipartFile file);
}
