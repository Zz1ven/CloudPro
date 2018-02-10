package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.RoomTime;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface RoomTimeMapper{

	@Insert("INSERT INTO tb_room_time(fk_tb_time_id,fk_tb_room_id,time) VALUES(#{fk_tb_time_id},#{fk_tb_room_id},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(RoomTime roomTime);

	@Update("UPDATE tb_room_time SET fk_tb_time_id=#{fk_tb_time_id},fk_tb_room_id=#{fk_tb_room_id},time=#{time} WHERE id=#{id}")
	int update(RoomTime roomTime);

	@Select("SELECT * FROM tb_room_time WHERE id=#{id}")
	@Results({@Result(column="fk_tb_time_id",property="fk_tb_time_id"),
			@Result(column="fk_tb_room_id",property="fk_tb_room_id"),
			@Result(property="timestamp",column="fk_tb_time_id",one=@One(select="com.avatarcn.tourists.mapper.TimestampMapper.selectByPrimaryKey")),
			@Result(property="room",column="fk_tb_room_id",one=@One(select="com.avatarcn.tourists.mapper.RoomMapper.selectByPrimaryKey"))})
	RoomTime selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_room_time limit #{offset},#{pageSize}")
	@Results({@Result(column="fk_tb_time_id",property="fk_tb_time_id"),
			@Result(column="fk_tb_room_id",property="fk_tb_room_id"),
			@Result(property="timestamp",column="fk_tb_time_id",one=@One(select="com.avatarcn.tourists.mapper.TimestampMapper.selectByPrimaryKey")),
			@Result(property="room",column="fk_tb_room_id",one=@One(select="com.avatarcn.tourists.mapper.RoomMapper.selectByPrimaryKey"))})
	List<RoomTime> selectPage(@Param(value = "offset") Integer offset,
                              @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_room_time")
	int count();

	@Delete("DELETE FROM tb_room_time WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_room_time  where fk_tb_time_id=#{fk_tb_time_id} and time=#{time}")
	@Results({
			@Result(column="fk_tb_time_id",property="fk_tb_time_id"),
			@Result(column="fk_tb_room_id",property="fk_tb_room_id"),
			@Result(property="timestamp",column="fk_tb_time_id",one=@One(select="com.avatarcn.tourists.mapper.TimestampMapper.selectByPrimaryKey")),
			@Result(property="room",column="fk_tb_room_id",one=@One(select="com.avatarcn.tourists.mapper.RoomMapper.selectByPrimaryKey"))})
	List<RoomTime> selectByTimePage(@Param(value = "fk_tb_time_id") Integer fk_tb_time_id, @Param(value = "time") Date time);

	@Select("SELECT COUNT(*) FROM tb_room_time WHERE fk_tb_time_id = #{fk_tb_time_id} AND fk_tb_room_id = #{fk_tb_room_id} AND time = #{time}")
	int selectByRoomAndTimeId(RoomTime roomTime);

}
