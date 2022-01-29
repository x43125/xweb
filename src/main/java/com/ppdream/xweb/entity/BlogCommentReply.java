package com.ppdream.xweb.entity;

import lombok.Data;

/**
 * @author x43125
 */
@Data
public class BlogCommentReply extends BaseEntity {

    private long commentUserId;
    private String commentReply;
    private String commentUserName;
    private long blogId;
    private String blogName;
    private long blogCommentId;


}
