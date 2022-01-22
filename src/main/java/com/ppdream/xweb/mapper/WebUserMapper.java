package com.ppdream.xweb.mapper;

import com.ppdream.xweb.entity.WebUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WebUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WebUser record);

    int insertSelective(WebUser record);

    WebUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WebUser record);

    int updateByPrimaryKey(WebUser record);

    WebUser selectByUsername(String username);

    List<WebUser> selectAll();
}