package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.Recommendation;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by z1ven on 2018/1/31 16:10
 */
@Mapper
public interface RecommendationMapper {

    @Insert("INSERT INTO tb_recommendation(image) VALUES(#{image})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Recommendation recommendation);

    @Delete("DELETE FROM tb_recommendation WHERE id = #{id}")
    int deleteById(Integer id);

    @Select("SELECT * FROM tb_recommendation WHERE id = #{id}")
    Recommendation selectById(Integer id);

    @Select("SELECT * FROM tb_recommendation")
    List<Recommendation> selectAll();

    @Update("UPDATE tb_recommendation SET image = #{image} WHERE id = #{id}")
    int update(Recommendation recommendation);
}
