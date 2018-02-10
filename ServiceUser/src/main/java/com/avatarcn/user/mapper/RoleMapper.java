package com.avatarcn.user.mapper;

import com.avatarcn.user.model.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by z1ven on 2018/2/9 11:40
 */
@Mapper
public interface RoleMapper {

    @Insert("INSERT INTO tb_role(name) VALUES(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Role role);

    @Delete("DELETE FROM tb_role WHERE id = #{id}")
    int deleteById(Integer id);

    @Select("SELECT * FROM tb_role WHERE id = #{id}")
    Role selectById(Integer id);

    @Select("SELECT * FROM tb_role WHERE name = #{name}")
    Role selectByName(String name);

    @Select("SELECT * FROM tb_role LIMIT #{offset}, #{pageSize}")
    List<Role> selectPage(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_role")
    int count();

    @Update("UPDATE tb_role SET name = #{name} WHERE id = #{id}")
    int update(Role role);
}
