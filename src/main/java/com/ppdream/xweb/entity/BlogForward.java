package com.ppdream.xweb.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * blog_forward
 * @author 
 */
@Data
@TableName(value = "blog_forward", schema = "public")
public class BlogForward implements Serializable {
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