package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.PromotionRecord;
import org.apache.ibatis.annotations.*;

/**
 * Created by z1ven on 2018/2/8 09:11
 */
@Mapper
public interface PromotionRecordMapper {

    @Insert("INSERT INTO tb_promotion_record(fk_tb_promotion_id, phone, time) VALUES(#{fk_tb_promotion_id}, #{phone}, #{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(PromotionRecord promotionRecord);

    @Delete("DELETE FROM tb_promotion_record WHERE id = #{id}")
    int delete(Integer id);

    @Delete("DELETE FROM tb_promotion_record WHERE fk_tb_promotion_id = #{fk_tb_promotion_id}")
    int deleteByPromotionId(Integer fk_tb_promotion_id);

    @Select("SELECT * FROM tb_promotion_record WHERE id = #{id}")
    PromotionRecord selectById(Integer id);

    @Select("SELECT * FROM tb_promotion_record WHERE fk_tb_promotion_id = #{fk_tb_promotion_id} AND phone = #{phone}")
    PromotionRecord selectByPromotionIdAndPhone(@Param("fk_tb_promotion_id") Integer fk_tb_promotion_id, @Param("phone") String phone);

    @Update("UPDATE tb_promotion_record SET fk_tb_promotion_id = #{fk_tb_promotion_id}, phone = #{phone}, time = #{time} WHERE id = #{id}")
    int update(PromotionRecord promotionRecord);
}
