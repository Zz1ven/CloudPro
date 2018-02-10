package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.FeedbackType;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by z1ven on 2018/1/26 09:08
 */
@Mapper
public interface FeedbackTypeMapper {

    @Insert("INSERT INTO tb_feedback_type(name) VALUES(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(FeedbackType feedbackType);

    @Delete("DELETE FROM tb_feedback_type WHERE id = #{id}")
    int deleteById(Integer id);

    @Select("SELECT * FROM tb_feedback_type WHERE id = #{id}")
    FeedbackType selectById(Integer id);

    @Select("SELECT * FROM tb_feedback_type WHERE name = #{name}")
    FeedbackType selectByName(String name);

    @Select("SELECT * FROM tb_feedback_type")
    List<FeedbackType> selectAll();

    @Update("UPDATE tb_feedback_type SET name = #{name} WHERE id = #{id}")
    int update(FeedbackType feedbackType);
}
