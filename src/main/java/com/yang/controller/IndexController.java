package com.yang.controller;

import com.yang.dto.PageDto;
import com.yang.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administrator
 */
@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;
    @RequestMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "5")Integer size
                        ){

        PageDto pageDto = questionService.questionLists(page,size);
        model.addAttribute("pageDto",pageDto);
        return "index";
    }
}
