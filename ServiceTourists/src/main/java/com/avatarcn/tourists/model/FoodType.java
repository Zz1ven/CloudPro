package com.avatarcn.tourists.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("")
public class FoodType{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private String name;
	@ApiModelProperty("")
	private String type_img;
	@ApiModelProperty("")
	private Date time;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getType_img(){
		return type_img;
	}

	public void setType_img(String type_img){
		this.type_img = type_img;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

}
