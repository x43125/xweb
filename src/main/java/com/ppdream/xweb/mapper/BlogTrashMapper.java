package com.ppdream.xweb.mapper;

import com.ppdream.xweb.entity.BlogTrash;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogTrashMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlogTrash record);

    int insertSelective(BlogTrash record);

    BlogTrash selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlogTrash record);

    int updateByPrimaryKey(BlogTrash record);
}