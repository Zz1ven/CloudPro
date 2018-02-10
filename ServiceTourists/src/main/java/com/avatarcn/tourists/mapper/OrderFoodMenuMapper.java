package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.OrderFoodMenu;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;


@Mapper
public interface OrderFoodMenuMapper{

	@Insert("INSERT INTO tb_order_food_menu(fk_tb_order_food_id, fk_tb_food_id, amount) VALUES(#{fk_tb_order_food_id}, #{fk_tb_food_id}, #{amount})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(OrderFoodMenu orderFoodMenu);

	@Update("UPDATE tb_order_food_menu SET fk_tb_order_food_id=#{fk_tb_order_food_id},fk_tb_food_id=#{fk_tb_food_id},amount=#{amount} WHERE id=#{id}")
	int update(OrderFoodMenu orderFoodMenu);

	@Select("SELECT * FROM tb_order_food_menu WHERE id=#{id}")
	OrderFoodMenu selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_order_food_menu limit #{offset},#{pageSize}")
	List<OrderFoodMenu> selectPage(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT * FROM tb_order_food_menu WHERE fk_tb_order_food_id=#{fk_tb_order_food_id}")
	@Results({
			@Result(property = "fk_tb_food_id", column = "fk_tb_food_id"),
			@Result(property = "food", column = "fk_tb_food_id", one = @One(select = "com.avatarcn.tourists.mapper.FoodMapper.selectByPrimaryKey", fetchType = FetchType.LAZY))
	})
	List<OrderFoodMenu> selectAll(@Param(value = "fk_tb_order_food_id") Integer fk_tb_order_food_id);

	@Select("SELECT COUNT(*) FROM tb_order_food_menu")
	int count();

	@Delete("DELETE FROM tb_order_food_menu WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

	@Delete("DELETE FROM tb_order_food_menu WHERE fk_tb_order_food_id=#{fk_tb_order_food_id}")
	int deleteByOrderFoodId(@Param(value = "fk_tb_order_food_id") Integer fk_tb_order_food_id);

}
