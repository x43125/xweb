package com.ppdream.xweb.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * blog_comment
 * @author 
 */
@Data
public class BlogComment implements Serializable {
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
     * 评论者id
     */
    private Long commentUserId;

    /**
     * 评论者名
     */
    private String commentUserName;

    /**
     * 评论内容
     */
    private String comment;

    private Date updateTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}