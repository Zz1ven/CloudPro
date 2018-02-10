package com.avatarcn.tourists.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("")
@JsonIgnoreProperties(value = {"handler"})
public class CouponsStatus{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private String status_number;
	@ApiModelProperty("")
	private String description;
	@ApiModelProperty("")
	private Date time;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getStatus_number(){
		return status_number;
	}

	public void setStatus_number(String status_number){
		this.status_number = status_number;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

}
