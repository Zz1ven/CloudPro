package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.Promotion;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by z1ven on 2018/2/7 17:38
 */
@Mapper
public interface PromotionMapper {

    @Insert("INSERT INTO tb_promotion(name, description, url, time) VALUES(#{name}, #{description}, #{url}, #{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Promotion promotion);

    @Delete("DELETE FROM tb_promotion WHERE id = #{id}")
    int delete(Integer id);

    @Select("SELECT * FROM tb_promotion WHERE id = #{id}")
    Promotion selectById(Integer id);

    @Select("SELECT * FROM tb_promotion ORDER BY time DESC LIMIT #{offset}, #{pageSize}")
    List<Promotion> selectPage(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_promotion")
    int count();

    @Update("UPDATE tb_promotion SET name = #{name}, description = #{description}, url = #{url}, time = #{time} WHERE id = #{id}")
    int update(Promotion promotion);
}
