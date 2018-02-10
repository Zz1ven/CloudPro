package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.ActiveOrder;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * Created by z1ven on 2018/1/19 15:53
 */
@Mapper
public interface ActiveOrderMapper {

    @Insert("INSERT INTO tb_order_active(fk_tb_active_id, fk_tb_user_id, fk_tb_order_status_id, number, amount, total_money, real_money, tourist_name, tourist_phone, time) VALUES(#{fk_tb_active_id}, #{fk_tb_user_id}, #{fk_tb_order_status_id}, #{number}, #{amount}, #{total_money}, #{real_money}, #{tourist_name}, #{tourist_phone}, #{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ActiveOrder activeOrder);

    @Delete("DELETE FROM tb_order_active WHERE id = #{id}")
    int deleteById(Integer id);

    @Select("SELECT * FROM tb_order_active WHERE id = #{id}")
    ActiveOrder selectById(Integer id);

    @Select("SELECT * FROM tb_order_active WHERE id = #{id}")
    @Results({
            @Result(property = "fk_tb_order_status_id", column = "fk_tb_order_status_id"),
            @Result(property = "orderStatus", column = "fk_tb_order_status_id", one = @One(select = "com.avatarcn.tourists.mapper.OrderStatusMapper.selectById", fetchType = FetchType.LAZY))
    })
    ActiveOrder selectCascadeById(Integer id);

    @Select("SELECT * FROM tb_order_active WHERE fk_tb_user_id = #{fk_tb_user_id} ORDER BY time DESC LIMIT #{offset}, #{pageSize}")
    @Results({
            @Result(property = "fk_tb_order_status_id", column = "fk_tb_order_status_id"),
            @Result(property = "orderStatus", column = "fk_tb_order_status_id", one = @One(select = "com.avatarcn.tourists.mapper.OrderStatusMapper.selectById", fetchType = FetchType.LAZY))
    })
    List<ActiveOrder> selectPageByUserId(@Param("fk_tb_user_id") Integer fk_tb_user_id, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("SELECT * FROM tb_order_active WHERE fk_tb_active_id = #{fk_tb_active_id}")
    List<ActiveOrder> selectByActiveId(Integer fk_tb_active_id);

    @Select("SELECT COUNT(*) FROM tb_order_active WHERE fk_tb_user_id = #{fk_tb_user_id}")
    int countByUserId(Integer fk_tb_user_id);

    @Update("UPDATE tb_order_active SET fk_tb_active_id = #{fk_tb_active_id}, fk_tb_user_id = #{fk_tb_user_id}, fk_tb_order_status_id = #{fk_tb_order_status_id}, number = #{number}, amount = #{amount}, total_money = #{total_money}, real_money = #{real_money}, tourist_name = #{tourist_name}, tourist_phone = #{tourist_phone}, time = #{time} WHERE id = #{id}")
    int update(ActiveOrder activeOrder);
}
