package com.avatarcn.tourists.mapper;

import com.avatarcn.tourists.model.Room;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;


@Mapper
public interface RoomMapper{

	@Insert("INSERT INTO tb_room(img,name,number,people,time) VALUES(#{img},#{name},#{number},#{people},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(Room room);

	@Update("UPDATE tb_room SET img=#{img},name=#{name},number=#{number},people=#{people},time=#{time} WHERE id=#{id}")
	int update(Room room);

	@Select("SELECT * FROM tb_room WHERE id=#{id}")
	Room selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_room limit #{offset},#{pageSize}")
	List<Room> selectPage(@Param(value = "offset") Integer offset,
                          @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_room")
	int count();

	@Delete("DELETE FROM tb_room WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);


	@Select("SELECT img FROM tb_room WHERE id = #{id}")
	String selectUrlById(Integer id);

	@Select("SELECT * FROM tb_room WHERE id NOT IN(SELECT fk_tb_room_id FROM tb_room_time where fk_tb_time_id=#{fk_tb_time_id} and time=#{time})")
	List<Room> selectByTimeNoPage(@Param(value = "fk_tb_time_id") Integer fk_tb_time_id, @Param(value = "time") Date time);

	@Select("SELECT COUNT(*) FROM tb_room WHERE id NOT IN(SELECT fk_tb_room_id FROM tb_room_time where fk_tb_time_id=#{fk_tb_time_id} and time=#{time})")
	int countByTimeNo(@Param(value = "fk_tb_time_id") Integer fk_tb_time_id, @Param(value = "time") Date time);

	@Select("SELECT * FROM tb_room WHERE id IN(SELECT fk_tb_room_id FROM tb_room_time where fk_tb_time_id=#{fk_tb_time_id} and time=#{time})")
	List<Room> selectByTimeInPage(@Param(value = "fk_tb_time_id") Integer fk_tb_time_id, @Param(value = "time") Date time);

	@Select("SELECT COUNT(*) FROM tb_room WHERE id IN(SELECT fk_tb_room_id FROM tb_room_time where fk_tb_time_id=#{fk_tb_time_id} and time=#{time})")
	int countByTimeIn(@Param(value = "fk_tb_time_id") Integer fk_tb_time_id, @Param(value = "time") Date time);
}
