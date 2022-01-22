package com.ppdream.xweb.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * blog_trash
 * @author 
 */
@Data
@TableName(value = "blog_trash", schema = "public")
public class BlogTrash implements Serializable {
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