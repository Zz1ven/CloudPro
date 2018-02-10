package com.avatarcn.tourists.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;

@ApiModel("")
public class RoomTime{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private Integer fk_tb_time_id;
	@ApiModelProperty("外键对象-")
	private Timestamp timestamp;
	@ApiModelProperty("")
	private Integer fk_tb_room_id;
	@ApiModelProperty("外键对象-")
	private Room room;
	@ApiModelProperty("")
	private Date time;

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getFk_tb_time_id(){
		return fk_tb_time_id;
	}

	public void setFk_tb_time_id(Integer fk_tb_time_id){
		this.fk_tb_time_id = fk_tb_time_id;
	}

	public Integer getFk_tb_room_id(){
		return fk_tb_room_id;
	}

	public void setFk_tb_room_id(Integer fk_tb_room_id){
		this.fk_tb_room_id = fk_tb_room_id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
