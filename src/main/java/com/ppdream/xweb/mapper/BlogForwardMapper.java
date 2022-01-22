package com.ppdream.xweb.mapper;

import com.ppdream.xweb.entity.BlogForward;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogForwardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlogForward record);

    int insertSelective(BlogForward record);

    BlogForward selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlogForward record);

    int updateByPrimaryKey(BlogForward record);
}