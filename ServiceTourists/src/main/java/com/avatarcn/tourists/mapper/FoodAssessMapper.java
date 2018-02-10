package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.FoodAssess;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface FoodAssessMapper{

	@Insert("INSERT INTO tb_food_assess(fk_tb_user_id,fk_tb_food_order_id,score,assess,time) VALUES(#{fk_tb_user_id},#{fk_tb_food_order_id},#{score},#{assess},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(FoodAssess foodAssess);

	@Update("UPDATE tb_food_assess SET fk_tb_user_id=#{fk_tb_user_id},fk_tb_food_order_id=#{fk_tb_food_order_id},score=#{score},assess=#{assess},time=#{time} WHERE id=#{id}")
	int update(FoodAssess foodAssess);

	@Select("SELECT * FROM tb_food_assess WHERE id=#{id}")
	FoodAssess selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_food_assess WHERE fk_tb_food_order_id=#{fk_tb_food_order_id} ORDER BY time DESC limit #{offset},#{pageSize}")
	List<FoodAssess> selectPage(@Param(value = "fk_tb_food_order_id") Integer fk_tb_food_order_id,
                                @Param(value = "offset") Integer offset,
                                @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_food_assess WHERE fk_tb_food_order_id=#{fk_tb_food_order_id}")
	int count(@Param(value = "fk_tb_food_order_id") Integer fk_tb_food_order_id);

	@Delete("DELETE FROM tb_food_assess WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT COUNT(*) FROM tb_food_assess WHERE fk_tb_user_id = #{fk_tb_user_id} AND fk_tb_food_order_id=#{fk_tb_food_order_id}")
	int countByUserIdAndFoodId(@Param("fk_tb_user_id") Integer fk_tb_user_id, @Param("fk_tb_food_order_id") Integer fk_tb_food_order_id);

	@Select("SELECT SUM(score) FROM tb_food_assess WHERE fk_tb_food_order_id=#{fk_tb_food_order_id}")
	float sumScoreWith(@Param("fk_tb_food_order_id") Integer fk_tb_food_order_id);

}
