package com.yang.dto;

import com.yang.model.User;
import lombok.Data;

@Data
public class QuestionDto {
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private int commentCount;
    private int viewCount;
    private int likeCount;
    private int creator;
    private String tag;
    private User user;
}
