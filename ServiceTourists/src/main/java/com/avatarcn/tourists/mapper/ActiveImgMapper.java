package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.ActiveImg;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by z1ven on 2018/1/18 14:42
 */
@Mapper
public interface ActiveImgMapper {

    @Insert("INSERT INTO tb_active_img(fk_tb_active_id, image, time) VALUES(#{fk_tb_active_id}, #{image}, #{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ActiveImg activeImg);

    @Delete("DELETE FROM tb_active_img WHERE id = #{id}")
    int deleteById();

    @Delete("DELETE FROM tb_active_img WHERE fk_tb_active_id = #{fk_tb_active_id}")
    int deleteByActiveId(Integer fk_tb_active_id);

    @Delete("DELETE FROM tb_active_img WHERE fk_tb_active_id = #{fk_tb_active_id} AND image = #{image}")
    int deleteByActiveIdAndUrl(@Param("fk_tb_active_id") Integer fk_tb_active_id, @Param("image") String image);

    @Select("SELECT image FROM tb_active_img WHERE fk_tb_active_id = #{fk_tb_active_id}")
    List<String> selectUrlByActiveId(Integer fk_tb_active_id);
}
