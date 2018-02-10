package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.Active;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * Created by z1ven on 2018/1/18 13:33
 */
@Mapper
public interface ActiveMapper {

    @Insert("INSERT INTO tb_active(name, icon, start_time, end_time, price, description, time) VALUES(#{name}, #{icon}, #{start_time}, #{end_time}, #{price}, #{description}, #{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Active active);

    @Delete("DELETE FROM tb_active WHERE id = #{id}")
    int deleteById(Integer id);

    @Select("SELECT * FROM tb_active WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "activeRemarkList", column = "id", many = @Many(select = "com.avatarcn.tourists.mapper.ActiveRemarkMapper.selectByActiveId", fetchType = FetchType.LAZY))
    })
    Active selectById(Integer id);

    @Select("SELECT * FROM tb_active WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "activeRemarkList", column = "id", many = @Many(select = "com.avatarcn.tourists.mapper.ActiveRemarkMapper.selectByActiveId", fetchType = FetchType.LAZY)),
            @Result(property = "imageList", column = "id", many = @Many(select = "com.avatarcn.tourists.mapper.ActiveImgMapper.selectUrlByActiveId", fetchType = FetchType.LAZY))
    })
    Active selectCascadeById(Integer id);

    @Select("SELECT * FROM tb_active ORDER BY time DESC LIMIT #{offset}, #{pageSize}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "activeRemarkList", column = "id", many = @Many(select = "com.avatarcn.tourists.mapper.ActiveRemarkMapper.selectByActiveId", fetchType = FetchType.LAZY))
    })
    List<Active> selectPage(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_active")
    int count();

    @Update("UPDATE tb_active SET name = #{name}, icon = #{icon}, start_time = #{start_time}, end_time = #{end_time}, price = #{price}, description = #{description}, time = #{time} WHERE id = #{id}")
    int update(Active active);
}
