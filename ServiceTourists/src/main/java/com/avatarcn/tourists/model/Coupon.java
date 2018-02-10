package com.avatarcn.tourists.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("")
@JsonIgnoreProperties(value = {"handler"})
public class Coupon{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private Integer fk_tb_coupons_type_id;
	@ApiModelProperty("")
	private String name;
	@ApiModelProperty("")
	private Float money;
	@ApiModelProperty("")
	private Float spend_money;
	@ApiModelProperty("")
	private String explains;
	@ApiModelProperty("")
	private Date valid_start_time;
	@ApiModelProperty("")
	private Date valid_end_time;
	@ApiModelProperty("")
	private Date create_time;

	private CouponsType couponsType;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getFk_tb_coupons_type_id(){
		return fk_tb_coupons_type_id;
	}

	public void setFk_tb_coupons_type_id(Integer fk_tb_coupons_type_id){
		this.fk_tb_coupons_type_id = fk_tb_coupons_type_id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Float getMoney(){
		return money;
	}

	public void setMoney(Float money){
		this.money = money;
	}

	public Float getSpend_money(){
		return spend_money;
	}

	public void setSpend_money(Float spend_money){
		this.spend_money = spend_money;
	}

	public String getExplains(){
		return explains;
	}

	public void setExplains(String explains){
		this.explains = explains;
	}

	public Date getValid_start_time(){
		return valid_start_time;
	}

	public void setValid_start_time(Date valid_start_time){
		this.valid_start_time = valid_start_time;
	}

	public Date getValid_end_time(){
		return valid_end_time;
	}

	public void setValid_end_time(Date valid_end_time){
		this.valid_end_time = valid_end_time;
	}

	public Date getCreate_time(){
		return create_time;
	}

	public void setCreate_time(Date create_time){
		this.create_time = create_time;
	}

	public CouponsType getCouponsType() {
		return couponsType;
	}

	public void setCouponsType(CouponsType couponsType) {
		this.couponsType = couponsType;
	}
}
