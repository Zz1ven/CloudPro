package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.json.response.FoodTypeResponse;
import com.avatarcn.tourists.model.FoodType;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;


@Mapper
public interface FoodTypeMapper{

	@Insert("INSERT INTO tb_food_type(name,type_img,time) VALUES(#{name},#{type_img},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(FoodType foodType);

	@Update("UPDATE tb_food_type SET name=#{name},type_img=#{type_img},time=#{time} WHERE id=#{id}")
	int update(FoodType foodType);

	@Select("SELECT * FROM tb_food_type WHERE id=#{id}")
	FoodType selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_food_type limit #{offset},#{pageSize}")
	List<FoodType> selectPage(@Param(value = "offset") Integer offset,
                              @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT * FROM tb_food_type")
	List<FoodType> selectAll();

	@Select("SELECT * FROM tb_food_type")
	@Results({
			@Result(property = "id", column = "id"),
			@Result(property = "foods", javaType = List.class,column ="id", many = @Many(select = "com.avatarcn.tourists.mapper.FoodMapper.selectAll", fetchType = FetchType.LAZY))
	})
	List<FoodTypeResponse> selectTypeFoods();

	@Select("SELECT COUNT(*) FROM tb_food_type")
	int count();

	@Delete("DELETE FROM tb_food_type WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

}
