package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.Feedback;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * Created by z1ven on 2018/1/26 08:58
 */
@Mapper
public interface FeedbackMapper {

    @Insert("INSERT INTO tb_feedback(fk_tb_user_id, fk_tb_feedback_type_id, content, email, phone, time) VALUES(#{fk_tb_user_id}, #{fk_tb_feedback_type_id}, #{content}, #{email}, #{phone}, #{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Feedback feedback);

    @Delete("DELETE FROM tb_feedback WHERE id = #{id}")
    int deleteById(Integer id);

    @Select("SELECT * FROM tb_feedback WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "fk_tb_feedback_type_id", column = "fk_tb_feedback_type_id"),
            @Result(property = "feedbackType", column = "fk_tb_feedback_type_id", one = @One(select = "com.avatarcn.tourists.mapper.FeedbackTypeMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "feedbackImgList", column = "id", many = @Many(select = "com.avatarcn.tourists.mapper.FeedbackImgMapper.selectByFeedbackId", fetchType = FetchType.LAZY)),
            @Result(property = "feedbackReplyList", column = "id", many = @Many(select = "com.avatarcn.tourists.mapper.FeedbackReplyMapper.selectByFeedbackId", fetchType = FetchType.LAZY))
    })
    Feedback selectById(Integer id);

    @Select("SELECT * FROM tb_feedback ORDER BY time DESC LIMIT #{offset}, #{pageSize}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "fk_tb_feedback_type_id", column = "fk_tb_feedback_type_id"),
            @Result(property = "feedbackType", column = "fk_tb_feedback_type_id", one = @One(select = "com.avatarcn.tourists.mapper.FeedbackTypeMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "feedbackImgList", column = "id", many = @Many(select = "com.avatarcn.tourists.mapper.FeedbackImgMapper.selectByFeedbackId", fetchType = FetchType.LAZY)),
            @Result(property = "feedbackReplyList", column = "id", many = @Many(select = "com.avatarcn.tourists.mapper.FeedbackReplyMapper.selectByFeedbackId", fetchType = FetchType.LAZY))
    })
    List<Feedback> selectPage(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("SELECT * FROM tb_feedback WHERE fk_tb_user_id = #{fk_tb_user_id} ORDER BY time DESC LIMIT #{offset}, #{pageSize}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "fk_tb_feedback_type_id", column = "fk_tb_feedback_type_id"),
            @Result(property = "feedbackType", column = "fk_tb_feedback_type_id", one = @One(select = "com.avatarcn.tourists.mapper.FeedbackTypeMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "feedbackImgList", column = "id", many = @Many(select = "com.avatarcn.tourists.mapper.FeedbackImgMapper.selectByFeedbackId", fetchType = FetchType.LAZY)),
            @Result(property = "feedbackReplyList", column = "id", many = @Many(select = "com.avatarcn.tourists.mapper.FeedbackReplyMapper.selectByFeedbackId", fetchType = FetchType.LAZY))
    })
    List<Feedback> selectPageByUserId(@Param("fk_tb_user_id") Integer fk_tb_user_id, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("SELECT * FROM tb_feedback WHERE fk_tb_feedback_type_id = #{fk_tb_feedback_type_id}")
    List<Feedback> selectByFeedbackTypeId(Integer fk_tb_feedback_type_id);

    @Select("SELECT COUNT(*) FROM tb_feedback WHERE fk_tb_user_id = #{fk_tb_user_id}")
    int countByUserId(Integer fk_tb_user_id);

    @Select("SELECT COUNT(*) FROM tb_feedback")
    int count();

    @Update("UPDATE tb_feedback SET fk_tb_user_id = #{fk_tb_user_id}, fk_tb_feedback_type_id = #{fk_tb_feedback_type_id}, content = #{content}, email = #{email}, phone = #{phone}, time = #{time} WHERE id = #{id}")
    int update(Feedback feedback);
}
