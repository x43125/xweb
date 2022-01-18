package com.ppdream.xweb.mapper;

import com.ppdream.xweb.entity.BlogCollect;

public interface BlogCollectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlogCollect record);

    int insertSelective(BlogCollect record);

    BlogCollect selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlogCollect record);

    int updateByPrimaryKey(BlogCollect record);
}