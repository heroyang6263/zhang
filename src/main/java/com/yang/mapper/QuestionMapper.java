package com.yang.mapper;

import com.yang.dto.QuestionDto;
import com.yang.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    @Select("select * from community.question limit #{offset},#{size}")
    List<Question> questionLists(@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);
    @Select("select count(1) from community.question")
    Integer count();
    @Select("select * from community.question where creator = #{userId} limit #{offset},#{size}")
    List<Question> questionListsByUserId(@Param(value = "offset") Integer offset,@Param(value = "size") Integer size,@Param(value = "userId") Integer userId);
    @Select("select * from community.question where id = #{id}")
    Question getById(@Param("id")Integer id);
}
