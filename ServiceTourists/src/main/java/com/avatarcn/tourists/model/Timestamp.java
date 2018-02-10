package com.avatarcn.tourists.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Time;

@ApiModel("")
public class Timestamp{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private Time start_time;
	@ApiModelProperty("")
	private Time end_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Time getStart_time() {
		return start_time;
	}

	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}

	public Time getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}
}
