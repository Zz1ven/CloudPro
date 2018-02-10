package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.Speciality;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;


@Mapper
public interface SpecialityMapper{

	@Insert("INSERT INTO tb_speciality(fk_tb_speciality_type_id, name,price,url,sale,remark,time) VALUES(#{fk_tb_speciality_type_id},#{name},#{price},#{url},#{sale},#{remark},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(Speciality speciality);

	@Update("UPDATE tb_speciality SET fk_tb_speciality_type_id=#{fk_tb_speciality_type_id},name=#{name},price=#{price},url=#{url},sale=#{sale},remark=#{remark},time=#{time} WHERE id=#{id}")
	int update(Speciality speciality);

	@Select("SELECT * FROM tb_speciality WHERE id=#{id}")
	@Results({
			@Result(property = "fk_tb_speciality_type_id", column = "fk_tb_speciality_type_id"),
			@Result(property = "specialityType", column = "fk_tb_speciality_type_id", one = @One(select = "com.avatarcn.tourists.mapper.SpecialityTypeMapper.selectById", fetchType = FetchType.LAZY))
	})
	Speciality selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_speciality limit #{offset},#{pageSize}")
	List<Speciality> selectPage(@Param(value = "offset") Integer offset,
                                @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT * FROM tb_speciality WHERE fk_tb_speciality_type_id = #{fk_tb_speciality_type_id} LIMIT #{offset}, #{pageSize}")
	List<Speciality> selectPageByTypeId(@Param(value = "fk_tb_speciality_type_id") Integer fk_tb_speciality_type_id, @Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT * FROM tb_speciality WHERE fk_tb_speciality_type_id = #{fk_tb_speciality_type_id}")
	List<Speciality> selectByTypeId(Integer fk_tb_speciality_type_id);

	@Select("SELECT COUNT(*) FROM tb_speciality")
	int count();

	@Select("SELECT COUNT(*) FROM tb_speciality WHERE fk_tb_speciality_type_id = #{fk_tb_speciality_type_id}")
	int countByTypeId(Integer fk_tb_speciality_type_id);

	@Delete("DELETE FROM tb_speciality WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

	@Delete("DELETE FROM tb_speciality WHERE fk_tb_speciality_type_id = #{fk_tb_speciality_type_id}")
	int deleteByTypeId(Integer fk_tb_speciality_type_id);
}
