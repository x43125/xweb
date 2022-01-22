package com.ppdream.xweb.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * blog_collect
 * @author 
 */
@Data
@TableName(value = "blog_collect", schema = "public")
public class BlogCollect implements Serializable {
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
     * 收藏者id
     */
    private Long collectUserId;

    /**
     * 收藏者名
     */
    private String collectUserName;

    private Date updateTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}