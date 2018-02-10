package com.avatarcn.user.mapper;

import com.avatarcn.user.model.UserRole;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * Created by z1ven on 2018/2/9 14:04
 */
@Mapper
public interface UserRoleMapper {

    @Insert("INSERT INTO tb_user_role(fk_tb_user_id, fk_tb_role_id) VALUES(#{fk_tb_user_id}, #{fk_tb_role_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(UserRole userRole);

    @Delete("DELETE FROM tb_user_role WHERE id = #{id}")
    int deleteById(Integer id);

    @Delete("DELETE FROM tb_user_role WHERE fk_tb_user_id = #{fk_tb_user_id}")
    int deleteByUserId(Integer fk_tb_user_id);

    @Delete("DELETE FROM tb_user_role WHERE fk_tb_role_id = #{fk_tb_role_id}")
    int deleteByRoleId(Integer fk_tb_role_id);

    @Select("SELECT * FROM tb_user_role WHERE id = #{id}")
    UserRole selectById(Integer id);

    @Select("SELECT * FROM tb_user_role WHERE fk_tb_user_id = #{fk_tb_user_id}")
    @Results({
            @Result(property = "fk_tb_role_id", column = "fk_tb_role_id"),
            @Result(property = "role", column = "fk_tb_role_id", one = @One(select = "com.avatarcn.user.mapper.RoleMapper.selectById", fetchType = FetchType.LAZY))
    })
    List<UserRole> selectByUserId(Integer fk_tb_user_id);

    @Select("SELECT * FROM tb_user_role WHERE fk_tb_user_id = #{fk_tb_user_id} AND fk_tb_role_id = #{fk_tb_role_id}")
    UserRole selectByUserIdAndRoleId(@Param("fk_tb_user_id") Integer fk_tb_user_id, @Param("fk_tb_role_id") Integer fk_tb_role_id);

    @Update("UPDATE tb_user_role SET fk_tb_user_id = #{fk_tb_user_id}, fk_tb_role_id = #{fk_tb_role_id} WHERE id = #{id}")
    int update(UserRole userRole);
}
