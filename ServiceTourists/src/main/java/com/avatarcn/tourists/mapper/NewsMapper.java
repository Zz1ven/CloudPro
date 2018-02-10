package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by z1ven on 2018/1/17 14:26
 */
@Mapper
public interface NewsMapper {

    @Insert("INSERT INTO tb_news(title, img, content, read_count, web_content, time) VALUES(#{title}, #{img}, #{content}, #{read_count}, #{web_content}, #{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(News news);

    @Delete("DELETE FROM tb_news WHERE id = #{id}")
    int delete(Integer id);

    @Select("SELECT * FROM tb_news WHERE id = #{id}")
    News selectById(Integer id);

    @Select("SELECT * FROM tb_news ORDER BY time DESC LIMIT #{offset}, #{pageSize}")
    List<News> selectPage(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_news")
    int count();

    @Update("UPDATE tb_news SET title = #{title}, img = #{img}, content = #{content}, read_count = #{read_count}, web_content = #{web_content}, time = #{time} WHERE id = #{id}")
    int update(News news);
}
