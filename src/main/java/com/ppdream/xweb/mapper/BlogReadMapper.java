package com.ppdream.xweb.mapper;

import com.ppdream.xweb.entity.BlogRead;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogReadMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlogRead record);

    int insertSelective(BlogRead record);

    BlogRead selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlogRead record);

    int updateByPrimaryKey(BlogRead record);
}