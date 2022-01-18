package com.ppdream.xweb.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * blog_read
 * @author 
 */
@Data
public class BlogRead implements Serializable {
    private Long id;

    /**
     * 博客名
     */
    private String blogName;

    /**
     * 博客id
     */
    private Long blogId;

    /**
     * 阅读者id
     */
    private Long readUserId;

    /**
     * 阅读者名
     */
    private String readUserName;

    private Date updateTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}