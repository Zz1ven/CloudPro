package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.CouponsUsers;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;


@Mapper
public interface CouponsUsersMapper{

	@Insert("INSERT INTO tb_coupons_users(fk_tb_order_active_id,fk_tb_coupons_status_id,fk_tb_user_id,fk_tb_coupon_id,fk_tb_order_food_id,visible,time) VALUES(#{fk_tb_order_active_id},#{fk_tb_coupons_status_id},#{fk_tb_user_id},#{fk_tb_coupon_id},#{fk_tb_order_food_id},#{visible},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(CouponsUsers couponsUsers);

	@Update("UPDATE tb_coupons_users SET fk_tb_order_active_id=#{fk_tb_order_active_id},fk_tb_coupons_status_id=#{fk_tb_coupons_status_id},fk_tb_user_id=#{fk_tb_user_id},fk_tb_coupon_id=#{fk_tb_coupon_id},fk_tb_order_food_id=#{fk_tb_order_food_id},visible=#{visible},time=#{time} WHERE id=#{id}")
	int update(CouponsUsers couponsUsers);

	@Select("SELECT * FROM tb_coupons_users WHERE id=#{id}")
	@Results({
			@Result(column="fk_tb_coupons_status_id",property="fk_tb_coupons_status_id"),
			@Result(column = "fk_tb_coupon_id", property = "fk_tb_coupon_id"),
			@Result(property="couponsStatus",column="fk_tb_coupons_status_id",one=@One(select="com.avatarcn.tourists.mapper.CouponsStatusMapper.selectByPrimaryKey",fetchType= FetchType.LAZY)),
			@Result(property = "coupon", column = "fk_tb_coupon_id", one = @One(select = "com.avatarcn.tourists.mapper.CouponMapper.selectByPrimaryKey", fetchType = FetchType.LAZY))
	})
	CouponsUsers selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_coupons_users limit #{offset},#{pageSize}")
	@Results({
			@Result(column="fk_tb_coupons_status_id",property="fk_tb_coupons_status_id"),
			@Result(column = "fk_tb_coupon_id", property = "fk_tb_coupon_id"),
			@Result(property="couponsStatus",column="fk_tb_coupons_status_id",one=@One(select="com.avatarcn.tourists.mapper.CouponsStatusMapper.selectByPrimaryKey",fetchType= FetchType.LAZY)),
			@Result(property = "coupon", column = "fk_tb_coupon_id", one = @One(select = "com.avatarcn.tourists.mapper.CouponMapper.selectByPrimaryKey", fetchType = FetchType.LAZY))
	})
	List<CouponsUsers> selectPage(@Param(value = "offset") Integer offset,
                                  @Param(value = "pageSize") Integer pageSize);


	@Select("SELECT * FROM tb_coupons_users WHERE fk_tb_user_id=#{fk_tb_user_id} ORDER BY time DESC")
	@Results({
			@Result(column="fk_tb_coupons_status_id",property="fk_tb_coupons_status_id"),
			@Result(column = "fk_tb_coupon_id", property = "fk_tb_coupon_id"),
			@Result(property="couponsStatus",column="fk_tb_coupons_status_id",one=@One(select="com.avatarcn.tourists.mapper.CouponsStatusMapper.selectByPrimaryKey",fetchType= FetchType.LAZY)),
			@Result(property = "coupon", column = "fk_tb_coupon_id", one = @One(select = "com.avatarcn.tourists.mapper.CouponMapper.selectByPrimaryKey", fetchType = FetchType.LAZY))
	})
	List<CouponsUsers> selectByUser(@Param(value = "fk_tb_user_id") Integer fk_tb_user_id);

   //有效的
	@Select("SELECT * FROM tb_coupons_users WHERE fk_tb_user_id=#{fk_tb_user_id} AND visible=1 ORDER BY time DESC ")
	@Results({
			@Result(column="fk_tb_coupons_status_id",property="fk_tb_coupons_status_id"),
			@Result(column = "fk_tb_coupon_id", property = "fk_tb_coupon_id"),
			@Result(property="couponsStatus",column="fk_tb_coupons_status_id",one=@One(select="com.avatarcn.tourists.mapper.CouponsStatusMapper.selectByPrimaryKey",fetchType= FetchType.LAZY)),
			@Result(property = "coupon", column = "fk_tb_coupon_id", one = @One(select = "com.avatarcn.tourists.mapper.CouponMapper.selectByPrimaryKey", fetchType = FetchType.LAZY))
	})
	List<CouponsUsers> selectByUserAndValid(@Param(value = "fk_tb_user_id") Integer fk_tb_user_id);

    //失效的
    @Select("SELECT * FROM tb_coupons_users WHERE fk_tb_user_id=#{fk_tb_user_id} AND visible=0 ORDER BY time DESC")
    @Results({
            @Result(column="fk_tb_coupons_status_id",property="fk_tb_coupons_status_id"),
            @Result(column = "fk_tb_coupon_id", property = "fk_tb_coupon_id"),
            @Result(property="couponsStatus",column="fk_tb_coupons_status_id",one=@One(select="com.avatarcn.tourists.mapper.CouponsStatusMapper.selectByPrimaryKey",fetchType= FetchType.LAZY)),
            @Result(property = "coupon", column = "fk_tb_coupon_id", one = @One(select = "com.avatarcn.tourists.mapper.CouponMapper.selectByPrimaryKey", fetchType = FetchType.LAZY))
    })
    List<CouponsUsers> selectByUserAndUnValid(@Param(value = "fk_tb_user_id") Integer fk_tb_user_id);


    @Select("SELECT COUNT(*) FROM tb_coupons_users")
	int count();

	@Select("SELECT COUNT(*) FROM tb_coupons_users WHERE fk_tb_user_id = #{fk_tb_user_id} AND visible=1")
	int countByUserId(Integer fk_tb_user_id);

    @Select("SELECT COUNT(*) FROM tb_coupons_users WHERE fk_tb_user_id = #{fk_tb_user_id} AND visible=0")
    int countByUserIdUnValid(Integer fk_tb_user_id);

	@Delete("DELETE FROM tb_coupons_users WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

	@Delete("DELETE FROM tb_coupons_users WHERE fk_tb_coupon_id=#{fk_tb_coupon_id}")
	int deleteByCouponId(@Param(value = "fk_tb_coupon_id") Integer fk_tb_coupon_id);

	@Delete("DELETE FROM tb_coupons_users WHERE fk_tb_order_active_id = #{fk_tb_order_active_id}")
	int deleteByActiveOrderId(Integer fk_tb_order_active_id);

	@Select("SELECT * FROM tb_coupons_users WHERE fk_tb_order_active_id = #{fk_tb_order_active_id}")
	@Results({
			@Result(column = "fk_tb_coupon_id", property = "fk_tb_coupon_id"),
			@Result(property = "coupon", column = "fk_tb_coupon_id", one = @One(select = "com.avatarcn.tourists.mapper.CouponMapper.selectByPrimaryKey", fetchType = FetchType.LAZY))
	})
	List<CouponsUsers> selectByActiveOrderId(Integer fk_tb_order_active_id);

	@Select("SELECT * FROM tb_coupons_users WHERE fk_tb_order_food_id = #{fk_tb_order_food_id}")
	@Results({
			@Result(column = "fk_tb_coupon_id", property = "fk_tb_coupon_id"),
			@Result(property = "coupon", column = "fk_tb_coupon_id", one = @One(select = "com.avatarcn.tourists.mapper.CouponMapper.selectByPrimaryKey", fetchType = FetchType.LAZY))
	})
	List<CouponsUsers> selectByFoodOrderId(Integer fk_tb_order_food_id);
}
