package com.ppdream.xweb.dto.blog;

import lombok.Data;

/**
 * @Author: x43125
 * @Date: 22/01/17
 */
@Data
public class BlogInteractDto {
    private Long tourId;
    private String tourName;
    private String comment;
    private String forwardComment;
    private String trashComment;

    private Long blogId;
    private String blogName;
}
