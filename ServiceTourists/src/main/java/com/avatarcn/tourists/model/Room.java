package com.avatarcn.tourists.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("")
public class Room{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private String img;
	@ApiModelProperty("")
	private String name;
	@ApiModelProperty("")
	private String number;
	@ApiModelProperty("")
	private Integer people;
	@ApiModelProperty("")
	private Date time;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getImg(){
		return img;
	}

	public void setImg(String img){
		this.img = img;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getNumber(){
		return number;
	}

	public void setNumber(String number){
		this.number = number;
	}

	public Integer getPeople(){
		return people;
	}

	public void setPeople(Integer people){
		this.people = people;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

}
