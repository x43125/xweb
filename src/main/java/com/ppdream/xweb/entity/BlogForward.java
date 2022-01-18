package com.ppdream.xweb.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * blog_forward
 * @author 
 */
@Data
public class BlogForward implements Serializable {
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
     * 转发者id
     */
    private Long forwardUserId;

    /**
     * 转发者名
     */
    private String forwardUserName;

    private Date updateTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}