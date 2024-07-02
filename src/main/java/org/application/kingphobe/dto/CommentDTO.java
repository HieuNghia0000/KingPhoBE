package org.application.kingphobe.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {

    private Integer commentId;
    private Integer productId;
    private Integer userId;
    private String content;
    private int rating;
    private Date commentDate;
}
