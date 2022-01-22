package com.ppdream.xweb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * web_user
 *
 * @author
 */
@Data
@TableName(value = "web_user", schema = "public")
public class WebUser extends BaseEntity {

    private String username;

    private String passwd;
}