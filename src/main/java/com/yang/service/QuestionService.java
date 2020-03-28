package com.yang.service;

import com.yang.dto.PageDto;
import com.yang.dto.QuestionDto;
import com.yang.mapper.QuestionMapper;
import com.yang.mapper.UserMapper;
import com.yang.model.Question;
import com.yang.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;
    public PageDto questionLists(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.questionLists(offset,size);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        PageDto pageDto = new PageDto();
        for (Question question : questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        pageDto.setQuestions(questionDtoList);
        Integer count = questionMapper.count();
        pageDto.setPagination(count,page,size);
        return pageDto;
    }

    public PageDto questionLists(Integer userId, Integer page, Integer size) {
        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.questionListsByUserId(offset,size,userId);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        PageDto pageDto = new PageDto();
        for (Question question : questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        pageDto.setQuestions(questionDtoList);
        Integer count = questionMapper.count();
        pageDto.setPagination(count,page,size);
        return pageDto;
    }

    public QuestionDto getById(Integer id) {
        Question  question = questionMapper.getById(id);
        User user = userMapper.findById(question.getCreator());
        QuestionDto questionDto = new QuestionDto();
        //将question赋值到questionDto中
        BeanUtils.copyProperties(question,questionDto);
        questionDto.setUser(user);
        return questionDto;
    }
}
