package com.ppdream.xweb.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * blog_read
 * @author 
 */
@Data
@TableName(value = "blog_read", schema = "public")
public class BlogRead implements Serializable {
    @TableId
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