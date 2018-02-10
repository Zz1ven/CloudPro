package com.avatarcn.user.mapper;

import com.avatarcn.user.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * Created by z1ven on 2018/2/9 10:31
 */
@Mapper
public interface UserMapper {

    @Insert("INSERT INTO tb_user(username, password, locked, enable, time) VALUES(#{username}, #{password}, #{locked}, #{enable}, #{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Delete("DELETE FROM tb_user WHERE id = #{id}")
    int deleteById(Integer id);

    @Select("SELECT * FROM tb_user WHERE id = #{id}")
    User selectById(Integer id);

    @Select("SELECT * FROM tb_user WHERE username = #{username}")
    User selectByUsername(String username);

    @Select("SELECT * FROM tb_user ORDER BY time DESC LIMIT #{offset}, #{pageSize}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userRoles", column = "id", many = @Many(select = "com.avatarcn.user.mapper.UserRoleMapper.selectByUserId", fetchType = FetchType.LAZY))
    })
    List<User> selectPage(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_user")
    int count();

    @Update("UPDATE tb_user SET username = #{username}, password = #{password}, locked = #{locked}, enable = #{enable}, time = #{time} WHERE id = #{id}")
    int update(User user);
}
