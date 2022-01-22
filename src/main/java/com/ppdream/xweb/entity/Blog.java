package com.ppdream.xweb.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * blog
 *
 * @author
 */
@Data
@TableName(value = "blog", schema = "public")
public class Blog implements Serializable {
    @TableId
    private Long id;

    /**
     * 博客名
     */
    private String name;

    /**
     * 博客标签
     */
    private String tag;

    /**
     * 博客图片
     */
    private String pic;

    private Integer readCount;

    private Integer likeCount;

    private Integer commentCount;

    private Integer forwardCount;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createTime;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date updateTime;

    /**
     * 博客名后缀
     */
    private String type;

    /**
     * 屏蔽量
     */
    private Integer trashCount;

    /**
     * 收藏数
     */
    private Integer collectCount;

    private static final long serialVersionUID = 1L;
}