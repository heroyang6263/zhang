package com.yang.controller;

import com.yang.dto.PageDto;
import com.yang.mapper.UserMapper;
import com.yang.model.User;
import com.yang.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;


/**
 * @author Administrator
 */
@Controller
public class ProfileController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/profile/{action}")
    public String profile(
            HttpServletRequest request,
            @PathVariable(name = "action")String action,
            Model model,
            @RequestParam(name = "page",defaultValue = "1")Integer page,
            @RequestParam(name = "size",defaultValue = "5")Integer size){
        //获取SessionInterceptor中上传到session中的user对应的值
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/";
        }
        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","我的回复");
        }
        PageDto pageDto = questionService.questionLists(user.getId(),page,size);
        model.addAttribute("pageDto",pageDto);
        return "profile";
    }
}
