package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.Adsense;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface AdsenseMapper{

	@Insert("INSERT INTO tb_adsense(name,remark,url,time,detailurl,content) VALUES(#{name},#{remark},#{url},#{time},#{detailurl},#{content})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(Adsense adsense);

	@Update("UPDATE tb_adsense SET name=#{name},remark=#{remark},url=#{url},time=#{time},detailurl=#{detailurl},content=#{content} WHERE id=#{id}")
	int update(Adsense adsense);

	@Select("SELECT * FROM tb_adsense WHERE id=#{id}")
	Adsense selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_adsense limit #{offset},#{pageSize}")
	List<Adsense> selectPage(@Param(value = "offset") Integer offset,
                             @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_adsense")
	int count();

	@Delete("DELETE FROM tb_adsense WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

}
