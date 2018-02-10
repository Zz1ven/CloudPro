package com.avatarcn.tourists.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("")
@JsonIgnoreProperties(value = {"handler"})
public class OrderFoodMenu{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private Integer fk_tb_order_food_id;
	@ApiModelProperty("")
	private Integer fk_tb_food_id;
	@ApiModelProperty("")
	private Integer amount;

	private Food food;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getFk_tb_order_food_id(){
		return fk_tb_order_food_id;
	}

	public void setFk_tb_order_food_id(Integer fk_tb_order_food_id){
		this.fk_tb_order_food_id = fk_tb_order_food_id;
	}

	public Integer getFk_tb_food_id(){
		return fk_tb_food_id;
	}

	public void setFk_tb_food_id(Integer fk_tb_food_id){
		this.fk_tb_food_id = fk_tb_food_id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}
}
