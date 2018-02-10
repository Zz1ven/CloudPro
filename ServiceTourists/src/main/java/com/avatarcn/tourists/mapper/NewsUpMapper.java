package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.NewsUp;
import org.apache.ibatis.annotations.*;

/**
 * Created by z1ven on 2018/1/17 15:20
 */
@Mapper
public interface NewsUpMapper {

    @Insert("INSERT INTO tb_news_up(fk_tb_news_id, fk_tb_user_id, time) VALUES(#{fk_tb_news_id}, #{fk_tb_user_id}, #{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(NewsUp newsUp);

    @Delete("DELETE FROM tb_news_up WHERE id = #{id}")
    int deleteById(Integer id);

    @Delete("DELETE FROM tb_news_up WHERE fk_tb_news_id = #{fk_tb_news_id}")
    int deleteByNewsId(Integer fk_tb_news_id);

    @Delete("DELETE FROM tb_news_up WHERE fk_tb_news_id = #{fk_tb_news_id} AND fk_tb_user_id = #{fk_tb_user_id}")
    int deleteByNewsIdAndUserId(@Param("fk_tb_news_id") Integer fk_tb_news_id, @Param("fk_tb_user_id") Integer fk_tb_user_id);

    @Select("SELECT COUNT(*) FROM tb_news_up WHERE fk_tb_news_id = #{fk_tb_news_id}")
    int countWithNewsId(Integer fk_tb_news_id);

    @Select("SELECT * FROM tb_news_up WHERE fk_tb_news_id = #{fk_tb_news_id} AND fk_tb_user_id = #{fk_tb_user_id}")
    NewsUp selectByNewsIdAndUserId(@Param("fk_tb_news_id") Integer fk_tb_news_id, @Param("fk_tb_user_id") Integer fk_tb_user_id);
}
