package com.ppdream.xweb.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * blog
 *
 * @author
 */
@Data
public class Blog implements Serializable {
    private Long id;

    private String name;

    private String tag;

    private String content;

    private String pic;

    private Integer readCount;

    private Integer likeCount;

    private Integer commentCount;

    private Integer forwardCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}