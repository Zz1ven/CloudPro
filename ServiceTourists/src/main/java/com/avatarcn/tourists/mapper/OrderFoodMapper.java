package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.OrderFood;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;


@Mapper
public interface OrderFoodMapper{

	@Insert("INSERT INTO tb_order_food(fk_tb_room_time_id,fk_tb_order_status_id,fk_tb_user_id,number,total_money,real_money,time) VALUES(#{fk_tb_room_time_id},#{fk_tb_order_status_id},#{fk_tb_user_id},#{number},#{total_money},#{real_money},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(OrderFood orderFood);

	@Update("UPDATE tb_order_food SET fk_tb_room_time_id=#{fk_tb_room_time_id},fk_tb_order_status_id=#{fk_tb_order_status_id},fk_tb_user_id=#{fk_tb_user_id},number=#{number},total_money=#{total_money},real_money=#{real_money},time=#{time} WHERE id=#{id}")
	int update(OrderFood orderFood);

	@Select("SELECT * FROM tb_order_food WHERE id=#{id}")
	OrderFood selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_order_food WHERE id=#{id}")
	@Results({
			@Result(property = "fk_tb_room_time_id", column = "fk_tb_room_time_id"),
			@Result(property = "roomTime", column = "fk_tb_room_time_id", one = @One(select = "com.avatarcn.tourists.mapper.RoomTimeMapper.selectByPrimaryKey", fetchType = FetchType.LAZY)),
			@Result(property = "fk_tb_order_status_id", column = "fk_tb_order_status_id"),
			@Result(property = "orderStatus", column = "fk_tb_order_status_id", one = @One(select = "com.avatarcn.tourists.mapper.OrderStatusMapper.selectById", fetchType = FetchType.LAZY)),
			@Result(property = "id", column = "id"),
			@Result(property = "orderFoodMenus", column = "id", many = @Many(select = "com.avatarcn.tourists.mapper.OrderFoodMenuMapper.selectAll", fetchType = FetchType.LAZY))
	})
	OrderFood selectCascadeById(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_order_food WHERE fk_tb_user_id = #{fk_tb_user_id} ORDER BY time DESC limit #{offset},#{pageSize}")
	@Results({
			@Result(property = "fk_tb_room_time_id", column = "fk_tb_room_time_id"),
			@Result(property = "roomTime", column = "fk_tb_room_time_id", one = @One(select = "com.avatarcn.tourists.mapper.RoomTimeMapper.selectByPrimaryKey", fetchType = FetchType.LAZY)),
			@Result(property = "fk_tb_order_status_id", column = "fk_tb_order_status_id"),
			@Result(property = "orderStatus", column = "fk_tb_order_status_id", one = @One(select = "com.avatarcn.tourists.mapper.OrderStatusMapper.selectById", fetchType = FetchType.LAZY)),
			@Result(property = "id", column = "id"),
			@Result(property = "orderFoodMenus", column = "id", many = @Many(select = "com.avatarcn.tourists.mapper.OrderFoodMenuMapper.selectAll", fetchType = FetchType.LAZY))
	})
	List<OrderFood> selectPage(@Param("fk_tb_user_id") Integer fk_tb_user_id, @Param(value = "offset") Integer offset,
                               @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_order_food")
	int count();

	@Delete("DELETE FROM tb_order_food WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

}
