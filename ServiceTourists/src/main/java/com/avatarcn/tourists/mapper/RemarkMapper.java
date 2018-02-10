package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.Remark;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by z1ven on 2018/1/18 14:07
 */
@Mapper
public interface RemarkMapper {

    @Insert("INSERT INTO tb_remark(name, time) VALUES(#{name}, #{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Remark remark);

    @Delete("DELETE FROM tb_remark WHERE id = #{id}")
    int deleteById(Integer id);

    @Select("SELECT * FROM tb_remark WHERE id = #{id}")
    Remark selectById(Integer id);

    @Select("SELECT name FROM tb_remark WHERE id = #{id}")
    String selectNameById(Integer id);

    @Select("SELECT * FROM tb_remark WHERE name = #{name}")
    Remark selectByName(String name);

    @Select("SELECT * FROM tb_remark")
    List<Remark> selectAll();

    @Update("UPDATE tb_remark SET name = #{name}, time = #{time} WHERE id = #{id}")
    int update(Remark remark);
}
