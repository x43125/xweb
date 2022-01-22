package com.ppdream.xweb.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * blog_like
 * @author 
 */
@Data
@TableName(value = "blog_like", schema = "public")
public class BlogLike implements Serializable {
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
     * 点赞者id
     */
    private Long likeUserId;

    /**
     * 点赞者名
     */
    private String likeUserName;

    private Date updateTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}