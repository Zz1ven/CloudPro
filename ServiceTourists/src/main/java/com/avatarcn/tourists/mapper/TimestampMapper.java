package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.Timestamp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TimestampMapper{

	@Insert("INSERT INTO tb_timestamp(start_time,end_time) VALUES(#{start_time},#{end_time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(Timestamp timestamp);

	@Update("UPDATE tb_timestamp SET start_time=#{start_time},end_time=#{end_time} WHERE id=#{id}")
	int update(Timestamp timestamp);

	@Select("SELECT * FROM tb_timestamp WHERE id=#{id}")
	@Results({})
	Timestamp selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_timestamp limit #{offset},#{pageSize}")
	@Results({})
	List<Timestamp> selectPage(@Param(value = "offset") Integer offset,
                               @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_timestamp")
	int count();

	@Select("SELECT * FROM tb_timestamp")
	List<Timestamp> selectAll();

	@Delete("DELETE FROM tb_timestamp WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

}
