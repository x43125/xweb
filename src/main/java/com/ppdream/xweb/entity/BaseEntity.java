package com.ppdream.xweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: x43125
 * @Date: 22/01/22
 */
@Data
public class BaseEntity implements Serializable {
    private Long id;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createTime;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
