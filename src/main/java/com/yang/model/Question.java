package com.yang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private int commentCount;
    private int viewCount;
    private int likeCount;
    private int creator;
    private String tag;
}
