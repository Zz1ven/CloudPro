package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.json.response.FoodResponse;
import com.avatarcn.tourists.model.Food;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;


@Mapper
public interface FoodMapper{

	@Insert("INSERT INTO tb_food(fk_tb_food_type_id,name,description,price,rebate_id,img,time) VALUES(#{fk_tb_food_type_id},#{name},#{description},#{price},#{rebate_id},#{img},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(Food food);

	@Update("UPDATE tb_food SET fk_tb_food_type_id=#{fk_tb_food_type_id},name=#{name},description=#{description},price=#{price},rebate_id=#{rebate_id},img=#{img},time=#{time} WHERE id=#{id}")
	int update(Food food);

	@Select("SELECT * FROM tb_food WHERE id=#{id}")
	Food selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_food WHERE fk_tb_food_type_id=#{fk_tb_food_type_id} limit #{offset},#{pageSize}")
	@Results({@Result(column="fk_tb_food_type_id",property="fk_tb_food_type_id"),
			@Result(property="foodType",column="fk_tb_food_type_id",one=@One(select="com.avatarcn.tourists.mapper.FoodTypeMapper.selectByPrimaryKey",fetchType= FetchType.LAZY))
	})
	List<FoodResponse> selectPage(@Param(value = "fk_tb_food_type_id") Integer fk_tb_food_type_id,
                                  @Param(value = "offset") Integer offset,
                                  @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_food WHERE fk_tb_food_type_id=#{fk_tb_food_type_id}")
	int count(@Param(value = "fk_tb_food_type_id") Integer fk_tb_food_type_id);

	@Delete("DELETE FROM tb_food WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

	@Delete("DELETE FROM tb_food WHERE fk_tb_food_type_id=#{fk_tb_food_type_id}")
	int deleteByFoodTypeId(@Param(value = "fk_tb_food_type_id") Integer fk_tb_food_type_id);

	@Select("SELECT * FROM tb_food WHERE fk_tb_food_type_id=#{fk_tb_food_type_id}")
	List<Food> selectAll(@Param(value = "fk_tb_food_type_id") Integer fk_tb_food_type_id);

}
