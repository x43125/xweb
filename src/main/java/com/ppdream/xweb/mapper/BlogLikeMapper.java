package com.ppdream.xweb.mapper;

import com.ppdream.xweb.entity.BlogLike;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BlogLikeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlogLike record);

    int insertSelective(BlogLike record);

    BlogLike selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlogLike record);

    int updateByPrimaryKey(BlogLike record);
}