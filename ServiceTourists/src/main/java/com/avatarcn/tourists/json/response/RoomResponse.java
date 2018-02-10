package com.avatarcn.tourists.json.response;
import com.avatarcn.tourists.model.Room;

import java.util.List;


public class RoomResponse {

	private List<Room> inRoom;
	private Integer inTotal;
	private List<Room> outRoom;
	private Integer outTotal;

	public List<Room> getInRoom() {
		return inRoom;
	}

	public void setInRoom(List<Room> inRoom) {
		this.inRoom = inRoom;
	}

	public Integer getInTotal() {
		return inTotal;
	}

	public void setInTotal(Integer inTotal) {
		this.inTotal = inTotal;
	}

	public List<Room> getOutRoom() {
		return outRoom;
	}

	public void setOutRoom(List<Room> outRoom) {
		this.outRoom = outRoom;
	}

	public Integer getOutTotal() {
		return outTotal;
	}

	public void setOutTotal(Integer outTotal) {
		this.outTotal = outTotal;
	}
}
