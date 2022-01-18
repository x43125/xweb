package com.ppdream.xweb.mapper;

import com.ppdream.xweb.entity.BlogComment;

public interface BlogCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlogComment record);

    int insertSelective(BlogComment record);

    BlogComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlogComment record);

    int updateByPrimaryKey(BlogComment record);
}