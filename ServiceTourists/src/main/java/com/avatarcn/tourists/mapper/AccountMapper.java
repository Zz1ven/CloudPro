package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.user.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * Created by z1ven on 2018/1/18 11:34
 */
@Mapper
public interface AccountMapper {

    @Insert("INSERT INTO tb_user(id, server_id, visible) VALUES(#{id}, #{server_id}, #{visible})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Account account);

    @Select("SELECT * FROM tb_user WHERE id = #{id} AND visible = 1")
    Account selectById(Integer id);

    @Select("SELECT * FROM tb_user WHERE server_id = #{server_id} AND visible = 1")
    Account selectByServerId(String server_id);
}
