package com.avatarcn.tourists.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("")
@JsonIgnoreProperties(value = {"handler"})
public class CouponsUsers{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private Integer fk_tb_order_active_id;
	@ApiModelProperty("")
	private Integer fk_tb_coupons_status_id;
	@ApiModelProperty("")
	private Integer fk_tb_user_id;
	@ApiModelProperty("")
	private Integer fk_tb_coupon_id;
	@ApiModelProperty("")
	private Integer fk_tb_order_food_id;
	@ApiModelProperty("")
	private Boolean visible;
	@ApiModelProperty("")
	private Date time;

	private CouponsStatus couponsStatus;
	private Coupon coupon;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getFk_tb_order_active_id(){
		return fk_tb_order_active_id;
	}

	public void setFk_tb_order_active_id(Integer fk_tb_order_active_id){
		this.fk_tb_order_active_id = fk_tb_order_active_id;
	}

	public Integer getFk_tb_coupons_status_id(){
		return fk_tb_coupons_status_id;
	}

	public void setFk_tb_coupons_status_id(Integer fk_tb_coupons_status_id){
		this.fk_tb_coupons_status_id = fk_tb_coupons_status_id;
	}

	public Integer getFk_tb_user_id(){
		return fk_tb_user_id;
	}

	public void setFk_tb_user_id(Integer fk_tb_user_id){
		this.fk_tb_user_id = fk_tb_user_id;
	}

	public Integer getFk_tb_coupon_id(){
		return fk_tb_coupon_id;
	}

	public void setFk_tb_coupon_id(Integer fk_tb_coupon_id){
		this.fk_tb_coupon_id = fk_tb_coupon_id;
	}

	public Integer getFk_tb_order_food_id(){
		return fk_tb_order_food_id;
	}

	public void setFk_tb_order_food_id(Integer fk_tb_order_food_id){
		this.fk_tb_order_food_id = fk_tb_order_food_id;
	}

	public Boolean getVisible(){
		return visible;
	}

	public void setVisible(Boolean visible){
		this.visible = visible;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

	public CouponsStatus getCouponsStatus() {
		return couponsStatus;
	}

	public void setCouponsStatus(CouponsStatus couponsStatus) {
		this.couponsStatus = couponsStatus;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
}
