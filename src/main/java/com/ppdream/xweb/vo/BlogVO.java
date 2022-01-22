package com.ppdream.xweb.vo;

import com.ppdream.xweb.dto.blog.BlogTouristDto;
import com.ppdream.xweb.entity.Blog;
import lombok.Data;

import java.util.List;

/**
 * @Author: x43125
 * @Date: 22/01/16
 */
@Data
public class BlogVO {
    private Blog blog;
    private String content;

    private List<BlogTouristDto> blogTouristDtoList;
}
