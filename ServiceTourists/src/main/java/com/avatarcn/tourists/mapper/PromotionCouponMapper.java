package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.PromotionCoupon;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * Created by z1ven on 2018/2/7 16:15
 */
@Mapper
public interface PromotionCouponMapper {

    @Insert("INSERT INTO tb_promotion_coupon(fk_tb_promotion_id, fk_tb_coupon_id) VALUES(#{fk_tb_promotion_id}, #{fk_tb_coupon_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(PromotionCoupon promotionCoupon);

    @Delete("DELETE FROM tb_promotion_coupon WHERE id = #{id}")
    int delete(Integer id);

    @Delete("DELETE FROM tb_promotion_coupon WHERE fk_tb_promotion_id = #{fk_tb_promotion_id}")
    int deleteByPromotionId(Integer fk_tb_promotion_id);

    @Delete("DELETE FROM tb_promotion_coupon WHERE fk_tb_coupon_id = #{fk_tb_coupon_id}")
    int deleteByCouponId(Integer fk_tb_coupon_id);

    @Select("SELECT * FROM tb_promotion_coupon WHERE id = #{id}")
    PromotionCoupon selectById(Integer id);

    @Select("SELECT * FROM tb_promotion_coupon WHERE fk_tb_promotion_id = #{fk_tb_promotion_id}")
    @Results({
            @Result(property = "fk_tb_coupon_id", column = "fk_tb_coupon_id"),
            @Result(property = "coupon", column = "fk_tb_coupon_id", one = @One(select = "com.avatarcn.tourists.mapper.CouponMapper.selectByPrimaryKey", fetchType = FetchType.LAZY))
    })
    List<PromotionCoupon> selectByPromotionId(Integer fk_tb_promotion_id);

    @Update("UPDATE tb_promotion_coupon SET fk_tb_promotion_id = #{fk_tb_promotion_id}, fk_tb_coupon_id = #{fk_tb_coupon_id} WHERE id = #{id}")
    int update(PromotionCoupon promotionCoupon);
}
