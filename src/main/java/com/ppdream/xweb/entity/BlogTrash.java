package com.ppdream.xweb.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * blog_trash
 * @author 
 */
@Data
public class BlogTrash implements Serializable {
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
     * 屏蔽者id
     */
    private Long trashUserId;

    /**
     * 屏蔽者名
     */
    private String trashUserName;

    private Date updateTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}