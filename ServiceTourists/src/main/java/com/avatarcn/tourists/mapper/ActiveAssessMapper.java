package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.ActiveAssess;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by z1ven on 2018/1/19 11:57
 */
@Mapper
public interface ActiveAssessMapper {

    @Insert("INSERT INTO tb_active_assess(fk_tb_user_id, fk_tb_active_id, score, assess, time) VALUES(#{fk_tb_user_id}, #{fk_tb_active_id}, #{score}, #{assess}, #{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ActiveAssess activeAssess);

    @Delete("DELETE FROM tb_active_assess WHERE id = #{id}")
    int deleteById(Integer id);

    @Delete("DELETE FROM tb_active_assess WHERE fk_tb_active_id = #{fk_tb_active_id}")
    int deleteByActiveId(Integer fk_tb_active_id);

    @Select("SELECT * FROM tb_active_assess WHERE id = #{id}")
    ActiveAssess selectById(Integer id);

    @Select("SELECT * FROM tb_active_assess WHERE fk_tb_active_id = #{fk_tb_active_id} ORDER BY time DESC LIMIT #{offset}, #{pageSize}")
    List<ActiveAssess> selectPageByActiveId(@Param("fk_tb_active_id") Integer fk_tb_active_id, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_active_assess WHERE fk_tb_active_id = #{fk_tb_active_id}")
    int countByActiveId(Integer fk_tb_active_id);

    @Select("SELECT COUNT(*) FROM tb_active_assess WHERE fk_tb_user_id = #{fk_tb_user_id} AND fk_tb_active_id = #{fk_tb_active_id}")
    int countByUserIdAndActiveId(@Param("fk_tb_user_id") Integer fk_tb_user_id, @Param("fk_tb_active_id") Integer fk_tb_active_id);

    @Select("SELECT SUM(score) FROM tb_active_assess WHERE fk_tb_active_id = #{fk_tb_active_id}")
    float sumScoreWithActive(Integer fk_tb_active_id);
}
