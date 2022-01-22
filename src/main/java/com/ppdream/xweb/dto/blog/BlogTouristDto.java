package com.ppdream.xweb.dto.blog;

import com.ppdream.xweb.entity.BlogComment;
import com.ppdream.xweb.entity.BlogCommentReply;
import com.ppdream.xweb.entity.WebUser;
import lombok.Data;

import java.util.List;

/**
 * @Author: x43125
 * @Date: 22/01/17
 */
@Data
public class BlogTouristDto {
    /**
     * 访问者信息
     */
    private WebUser touristUser;
    /**
     * 访问者所有评论内容，以及每条评论的回复
     */
    private List<BlogCommentReplyDto> blogCommentReplyDtoList;
}
