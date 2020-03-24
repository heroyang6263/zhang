package com.yang.mapper;

import com.yang.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
@Component
public interface QuestionMapper {
    @Insert("insert into community.question(title,description,gmt_create,gmt_modified,creator,tag ) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
    @Select("select * from community.question")
    List<Question> questionLists();
}
