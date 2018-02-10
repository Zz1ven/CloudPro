package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.SpecialityType;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by z1ven on 2018/1/30 13:04
 */
@Mapper
public interface SpecialityTypeMapper {

    @Insert("INSERT INTO tb_speciality_type(name, icon) VALUES(#{name}, #{icon})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(SpecialityType specialityType);

    @Delete("DELETE FROM tb_speciality_type WHERE id = #{id}")
    int deleteById(Integer id);

    @Select("SELECT * FROM tb_speciality_type WHERE id = #{id}")
    SpecialityType selectById(Integer id);

    @Select("SELECT COUNT(*) FROM tb_speciality_type WHERE name = #{name}")
    int countByName(String name);

    @Select("SELECT * FROM tb_speciality_type")
    List<SpecialityType> selectAll();

    @Update("UPDATE tb_speciality_type SET name = #{name}, icon = #{icon} WHERE id= #{id}")
    int update(SpecialityType specialityType);
}
