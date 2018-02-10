package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.CouponsStatus;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CouponsStatusMapper{

	@Insert("INSERT INTO tb_coupons_status(status_number,description,time) VALUES(#{status_number},#{description},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(CouponsStatus couponsStatus);

	@Update("UPDATE tb_coupons_status SET status_number=#{status_number},description=#{description},time=#{time} WHERE id=#{id}")
	int update(CouponsStatus couponsStatus);

	@Select("SELECT * FROM tb_coupons_status WHERE id=#{id}")
	CouponsStatus selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_coupons_status limit #{offset},#{pageSize}")
	List<CouponsStatus> selectPage(@Param(value = "offset") Integer offset,
                                   @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT * FROM tb_coupons_status")
	List<CouponsStatus> selectAll();

	@Select("SELECT COUNT(*) FROM tb_coupons_status")
	int count();

	@Delete("DELETE FROM tb_coupons_status WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

}
