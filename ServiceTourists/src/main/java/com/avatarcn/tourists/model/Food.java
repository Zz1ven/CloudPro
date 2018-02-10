package com.avatarcn.tourists.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("")
@JsonIgnoreProperties(value = {"handler"})
public class Food{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private Integer fk_tb_food_type_id;
	@ApiModelProperty("")
	private String name;
	@ApiModelProperty("")
	private String description;
	@ApiModelProperty("")
	private Float price;
	@ApiModelProperty("")
	private Float rebate_id;
	@ApiModelProperty("")
	private String img;
	@ApiModelProperty("")
	private Date time;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getFk_tb_food_type_id(){
		return fk_tb_food_type_id;
	}

	public void setFk_tb_food_type_id(Integer fk_tb_food_type_id){
		this.fk_tb_food_type_id = fk_tb_food_type_id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public Float getPrice(){
		return price;
	}

	public void setPrice(Float price){
		this.price = price;
	}

	public Float getRebate_id() {
		return rebate_id;
	}

	public void setRebate_id(Float rebate_id) {
		this.rebate_id = rebate_id;
	}

	public String getImg(){
		return img;
	}

	public void setImg(String img){
		this.img = img;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

}
