package com.ppdream.xweb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * user
 *
 * @author
 */
@Data
public class User implements Serializable {
    private Long id;

    private String username;

    private String passwd;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}