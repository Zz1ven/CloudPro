package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.ActiveRemark;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * Created by z1ven on 2018/1/18 14:17
 */
@Mapper
public interface ActiveRemarkMapper {

    @Insert("INSERT INTO tb_active_remark(fk_tb_remark_id, fk_tb_active_id) VALUES(#{fk_tb_remark_id}, #{fk_tb_active_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ActiveRemark activeRemark);

    @Delete("DELETE FROM tb_active_remark WHERE fk_tb_remark_id = #{fk_tb_remark_id}")
    int deleteByRemarkId(Integer fk_tb_remark_id);

    @Delete("DELETE FROM tb_active_remark WHERE fk_tb_active_id = #{fk_tb_active_id}")
    int deleteByActiveId(Integer fk_tb_active_id);

    @Delete("DELETE FROM tb_active_remark WHERE fk_tb_remark_id = #{fk_tb_remark_id} AND fk_tb_active_id = #{fk_tb_active_id}")
    int deleteByRemarkIdAndActiveId(@Param("fk_tb_remark_id") Integer fk_tb_remark_id, @Param("fk_tb_active_id") Integer fk_tb_active_id);

    @Select("SELECT * FROM tb_active_remark WHERE fk_tb_active_id = #{fk_tb_active_id}")
    @Results({
            @Result(property = "fk_tb_remark_id", column = "fk_tb_remark_id"),
            @Result(property = "remark_name", column = "fk_tb_remark_id", one = @One(select = "com.avatarcn.tourists.mapper.RemarkMapper.selectNameById", fetchType = FetchType.LAZY))
    })
    List<ActiveRemark> selectByActiveId(Integer fk_tb_active_id);

    @Select("SELECT * FROM tb_active_remark WHERE fk_tb_active_id = #{fk_tb_active_id} AND fk_tb_remark_id = #{fk_tb_remark_id}")
    @Results({
            @Result(property = "fk_tb_remark_id", column = "fk_tb_remark_id"),
            @Result(property = "remark_name", column = "fk_tb_remark_id", one = @One(select = "com.avatarcn.tourists.mapper.RemarkMapper.selectNameById", fetchType = FetchType.LAZY))
    })
    ActiveRemark selectByActiveIdAndRemarkId(@Param("fk_tb_active_id") Integer fk_tb_active_id, @Param("fk_tb_remark_id") Integer fk_tb_remark_id);
}
