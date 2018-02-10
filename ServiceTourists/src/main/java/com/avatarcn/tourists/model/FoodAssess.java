package com.avatarcn.tourists.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("")
public class FoodAssess{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private Integer fk_tb_user_id;
	@ApiModelProperty("")
	private Integer fk_tb_food_order_id;
	@ApiModelProperty("")
	private Float score;
	@ApiModelProperty("")
	private String assess;
	@ApiModelProperty("")
	private Date time;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getFk_tb_user_id(){
		return fk_tb_user_id;
	}

	public void setFk_tb_user_id(Integer fk_tb_user_id){
		this.fk_tb_user_id = fk_tb_user_id;
	}

	public Integer getFk_tb_food_order_id() {
		return fk_tb_food_order_id;
	}

	public void setFk_tb_food_order_id(Integer fk_tb_food_order_id) {
		this.fk_tb_food_order_id = fk_tb_food_order_id;
	}

	public Float getScore(){
		return score;
	}

	public void setScore(Float score){
		this.score = score;
	}

	public String getAssess(){
		return assess;
	}

	public void setAssess(String assess){
		this.assess = assess;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

}
