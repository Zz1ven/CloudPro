package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.NewsComment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by z1ven on 2018/1/17 15:19
 */
@Mapper
public interface NewsCommentMapper {

    @Insert("INSERT INTO tb_news_comment(fk_tb_user_id, fk_tb_news_id, content, time) VALUES(#{fk_tb_user_id}, #{fk_tb_news_id}, #{content}, #{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(NewsComment newsComment);

    @Delete("DELETE FROM tb_news_comment WHERE id = #{id}")
    int deleteById(Integer id);

    @Delete("DELETE FROM tb_news_comment WHERE fk_tb_news_id = #{fk_tb_news_id}")
    int deleteByNewsId(Integer fk_tb_news_id);

    @Select("SELECT COUNT(*) FROM tb_news_comment WHERE fk_tb_news_id = #{fk_tb_news_id}")
    int countWithNewsId(Integer fk_tb_news_id);

    @Select("SELECT * FROM tb_news_comment WHERE fk_tb_news_id = #{fk_tb_news_id} ORDER BY time DESC LIMIT #{offset}, #{pageSize}")
    List<NewsComment> selectPageByNewsId(@Param("fk_tb_news_id") Integer fk_tb_news_id, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
}
