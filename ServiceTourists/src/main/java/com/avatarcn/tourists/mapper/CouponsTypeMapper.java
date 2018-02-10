package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.CouponsType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CouponsTypeMapper{

	@Insert("INSERT INTO tb_coupons_type(code,remark,time) VALUES(#{code},#{remark},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(CouponsType couponsType);

	@Update("UPDATE tb_coupons_type SET code=#{code},remark=#{remark},time=#{time} WHERE id=#{id}")
	int update(CouponsType couponsType);

	@Select("SELECT * FROM tb_coupons_type WHERE id=#{id}")
	CouponsType selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_coupons_type limit #{offset},#{pageSize}")
	List<CouponsType> selectPage(@Param(value = "offset") Integer offset,
                                 @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT * FROM tb_coupons_type")
	List<CouponsType> selectAll();

	@Select("SELECT COUNT(*) FROM tb_coupons_type")
	int count();

	@Delete("DELETE FROM tb_coupons_type WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

}
