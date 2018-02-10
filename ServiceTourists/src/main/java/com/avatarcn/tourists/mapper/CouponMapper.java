package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.Coupon;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface CouponMapper{

	@Insert("INSERT INTO tb_coupon(fk_tb_coupons_type_id,name,money,spend_money,explains,valid_start_time,valid_end_time,create_time) VALUES(#{fk_tb_coupons_type_id},#{name},#{money},#{spend_money},#{explains},#{valid_start_time},#{valid_end_time},#{create_time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(Coupon coupon);

	@Update("UPDATE tb_coupon SET fk_tb_coupons_type_id=#{fk_tb_coupons_type_id},name=#{name},money=#{money},spend_money=#{spend_money},explains=#{explains},valid_start_time=#{valid_start_time},valid_end_time=#{valid_end_time},create_time=#{create_time} WHERE id=#{id}")
	int update(Coupon coupon);

	@Select("SELECT * FROM tb_coupon WHERE id=#{id}")
	@Results({
			@Result(property = "fk_tb_coupons_type_id", column = "fk_tb_coupons_type_id"),
			@Result(property = "couponsType", column = "fk_tb_coupons_type_id", one = @One(select = "com.avatarcn.tourists.mapper.CouponsTypeMapper.selectByPrimaryKey", fetchType = FetchType.LAZY))
	})
	Coupon selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_coupon ORDER BY create_time DESC limit #{offset},#{pageSize}")
	@Results({
			@Result(property = "fk_tb_coupons_type_id", column = "fk_tb_coupons_type_id"),
			@Result(property = "couponsType", column = "fk_tb_coupons_type_id", one = @One(select = "com.avatarcn.tourists.mapper.CouponsTypeMapper.selectByPrimaryKey", fetchType = FetchType.LAZY))
	})
	List<Coupon> selectPage(@Param(value = "offset") Integer offset,
                            @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT * FROM tb_coupon ORDER BY create_time DESC")
	@Results({
			@Result(property = "fk_tb_coupons_type_id", column = "fk_tb_coupons_type_id"),
			@Result(property = "couponsType", column = "fk_tb_coupons_type_id", one = @One(select = "com.avatarcn.tourists.mapper.CouponsTypeMapper.selectByPrimaryKey", fetchType = FetchType.LAZY))
	})
	List<Coupon> selectAll();

	@Select("SELECT * FROM tb_coupon WHERE fk_tb_coupons_type_id = #{fk_tb_coupons_type_id}")
	List<Coupon> selectByType(Integer fk_tb_coupons_type_id);

	@Select("SELECT COUNT(*) FROM tb_coupon")
	int count();

	@Delete("DELETE FROM tb_coupon WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

}
