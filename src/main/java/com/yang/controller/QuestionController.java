package com.yang.controller;

import com.yang.dto.QuestionDto;
import com.yang.mapper.QuestionMapper;
import com.yang.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
/**
 * @author Administrator
 * 完成问题详情控制层的编写
 */
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id,
                            Model model){
        QuestionDto questionDto = questionService.getById(id);
        model.addAttribute("quetionDto",questionDto);
        return "question";
    }
}
