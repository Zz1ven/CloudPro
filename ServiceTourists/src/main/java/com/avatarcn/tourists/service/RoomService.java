package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.global.Constant;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.json.response.RoomResponse;
import com.avatarcn.tourists.mapper.RoomMapper;
import com.avatarcn.tourists.mapper.RoomTimeMapper;
import com.avatarcn.tourists.mapper.TimestampMapper;
import com.avatarcn.tourists.model.Room;
import com.avatarcn.tourists.model.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class RoomService{
	@Autowired
	private RoomMapper roomMapper;
	@Autowired
	private OssService ossService;

	@Autowired
	private RoomTimeMapper roomTimeMapper;
	@Autowired
	private TimestampMapper timestampMapper;

	public Room insert(String img, String name, String number, Integer people)throws ErrorCodeException {
		Room room = new Room();
		String url = ossService.copyFileTo(img, Constant.TOURISTS_TMP_DIR, Constant.TOURISTS_ROOM_DIR);
		room.setImg(url);
		room.setName(name);
		room.setNumber(number);
		room.setPeople(people);
		room.setTime(new Date());
		roomMapper.insert(room);
		return room;
	}

	public Room update(Integer id,String img,String name,String number,Integer people) throws ErrorCodeException {
		Room room = roomMapper.selectByPrimaryKey(id);
		if(room == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		if(img != null&&!img.equals(room.getImg())){
			//删除之前
			String old_url = room.getImg();
			ossService.delete(old_url);
			//先移动文件位置
			String img_url = ossService.copyFileTo(img,Constant.TOURISTS_TMP_DIR, Constant.TOURISTS_ROOM_DIR);
			room.setImg(img_url);
		}
		if(name != null){
			room.setName(name);
		}
		if(number != null){
			room.setNumber(number);
		}
		if(people != null){
			room.setPeople(people);
		}
			room.setTime(new Date());
		   roomMapper.update(room);
		  return room;
	}

	public Room selectByPrimaryKey(Integer id) throws ErrorCodeException{
		Room room = roomMapper.selectByPrimaryKey(id);
		if(room == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return room;
	}

	public PageResponse<Room> selectPage(Integer offset, Integer pageSize){
		PageResponse<Room> response = new PageResponse();
		response.setItem(roomMapper.selectPage(offset,pageSize));
		response.setTotal(roomMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}


	public RoomResponse selectByTimePage(Date time, Integer start_time) throws ErrorCodeException{

		RoomResponse roomResponse=new RoomResponse();
		//根据数据库的当日时间段来判断
		Timestamp timestamp = timestampMapper.selectByPrimaryKey(start_time);
		if(timestamp==null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		//查出已经预定的房间号
		List<Room> rooms1 = roomMapper.selectByTimeInPage(start_time, time);
		int iIn = roomMapper.countByTimeIn(start_time, time);
		roomResponse.setInRoom(rooms1);
		roomResponse.setInTotal(iIn);

		//查出未预定的房间号
		List<Room> rooms = roomMapper.selectByTimeNoPage(start_time, time);
		int iOut = roomMapper.countByTimeNo(start_time, time);
		roomResponse.setOutRoom(rooms);
		roomResponse.setOutTotal(iOut);
		return roomResponse;
	}


	public int deleteByPrimaryKey(Integer id){
		//得到房间图片地址
		 String img=roomMapper.selectUrlById(id);
		//删除房间图片地址
		ossService.delete(img);
		return roomMapper.deleteByPrimaryKey(id);
	}

}
