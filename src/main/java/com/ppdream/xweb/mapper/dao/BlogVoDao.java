package com.ppdream.xweb.mapper.dao;

import com.ppdream.xweb.vo.BlogVO;

/**
 * @Author: x43125
 * @Date: 22/01/22
 */
public interface BlogVoDao {
    BlogVO selectByBlogName(String name);
}
