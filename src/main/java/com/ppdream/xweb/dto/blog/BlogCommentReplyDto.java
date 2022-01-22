package com.ppdream.xweb.dto.blog;

import lombok.Data;

import java.util.List;

/**
 * @Author: x43125
 * @Date: 22/01/22
 */
@Data
public class BlogCommentReplyDto {
    private String comment;
    private List<String> commentReply;
}
