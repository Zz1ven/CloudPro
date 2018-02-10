package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.FeedbackReply;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by z1ven on 2018/1/26 13:46
 */
@Mapper
public interface FeedbackReplyMapper {

    @Insert("INSERT INTO tb_feedback_reply(fk_tb_feedback_id, content, time) VALUES(#{fk_tb_feedback_id}, #{content}, #{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(FeedbackReply feedbackReply);

    @Delete("DELETE FROM tb_feedback_reply WHERE id = #{id}")
    int deleteById(Integer id);

    @Delete("DELETE FROM tb_feedback_reply WHERE fk_tb_feedback_id = #{fk_tb_feedback_id}")
    int deleteByFeedbackId(Integer fk_tb_feedback_id);

    @Select("SELECT * FROM tb_feedback_reply WHERE fk_tb_feedback_id = #{fk_tb_feedback_id}")
    List<FeedbackReply> selectByFeedbackId(Integer fk_tb_feedback_id);

    @Update("UPDATE tb_feedback_reply SET fk_tb_feedback_id = #{fk_tb_feedback_id}, content = #{content}, time = #{time} WHERE id = #{id}")
    int update(FeedbackReply feedbackReply);
}
