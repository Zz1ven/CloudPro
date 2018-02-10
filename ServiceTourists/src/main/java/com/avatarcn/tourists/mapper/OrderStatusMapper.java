package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.OrderStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by z1ven on 2018/1/19 16:05
 */
@Mapper
public interface OrderStatusMapper {

    @Select("SELECT * FROM tb_order_status WHERE id = #{id}")
    OrderStatus selectById(Integer id);
}
