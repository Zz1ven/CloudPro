package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.FeedbackImg;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by z1ven on 2018/1/26 09:13
 */
@Mapper
public interface FeedbackImgMapper {

    @Insert("INSERT INTO tb_feedback_img(fk_tb_feedback_id, image) VALUES(#{fk_tb_feedback_id}, #{image})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(FeedbackImg feedbackImg);

    @Delete("DELETE FROM tb_feedback_img WHERE id = #{id}")
    int deleteById(Integer id);

    @Delete("DELETE FROM tb_feedback_img WHERE fk_tb_feedback_id = #{fk_tb_feedback_id}")
    int deleteByFeedbackId(Integer fk_tb_feedback_id);

    @Select("SELECT * FROM tb_feedback_img WHERE id = #{id}")
    FeedbackImg selectById(Integer id);

    @Select("SELECT * FROM tb_feedback_img WHERE fk_tb_feedback_id = #{fk_tb_feedback_id}")
    List<FeedbackImg> selectByFeedbackId(Integer fk_tb_feedback_id);

    @Delete("UPDATE tb_feedback_img SET fk_tb_feedback_id = #{fk_tb_feedback_id}, image = #{image} WHERE id = #{id}")
    int update(FeedbackImg feedbackImg);
}
