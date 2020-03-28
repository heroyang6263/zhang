package com.yang.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDto {
    private List<QuestionDto> questions;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showFirstPage;
    private boolean showEndPage;
    private Integer currentPage;
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer count, Integer page, Integer size) {
        Integer totalPage = 0;
        if(count%size == 0){
            totalPage = count/size;
        }else {
            totalPage = count/size + 1;
        }
        pages.add(page);
        for (int i = 1;i <= 3;i++){
            if (page - i > 0){
                pages.add(0,page - i);
            }
            if (page + i <= totalPage){
                pages.add(page + i);
            }
        }


        if (page == 1){
            showPrevious = false;
        }else {
            showPrevious = true;
        }
        if (page.equals(totalPage)){
            showNext = false;
        }else {
            showNext = true;
        }
        if (pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }
        if (pages.contains(totalPage)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }
    }
}
