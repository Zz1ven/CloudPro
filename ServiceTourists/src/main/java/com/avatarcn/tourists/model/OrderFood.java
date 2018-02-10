package com.avatarcn.tourists.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel("")
@JsonIgnoreProperties(value = {"handler"})
public class OrderFood{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private Integer fk_tb_room_time_id;
	@ApiModelProperty("")
	private Integer fk_tb_order_status_id;
	@ApiModelProperty("")
	private Integer fk_tb_user_id;
	@ApiModelProperty("")
	private String number;
	@ApiModelProperty("δ")
	private Float total_money;
	@ApiModelProperty("")
	private Float real_money;
	@ApiModelProperty("")
	private Date time;

	private OrderStatus orderStatus;
	private List<OrderFoodMenu> orderFoodMenus;
	@ApiModelProperty("外键对象-")
	private RoomTime roomTime;

    public RoomTime getRoomTime() {
        return roomTime;
    }

    public void setRoomTime(RoomTime roomTime) {
        this.roomTime = roomTime;
    }

    public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getFk_tb_room_time_id() {
		return fk_tb_room_time_id;
	}

	public void setFk_tb_room_time_id(Integer fk_tb_room_time_id) {
		this.fk_tb_room_time_id = fk_tb_room_time_id;
	}

	public Integer getFk_tb_order_status_id(){
		return fk_tb_order_status_id;
	}

	public void setFk_tb_order_status_id(Integer fk_tb_order_status_id){
		this.fk_tb_order_status_id = fk_tb_order_status_id;
	}

	public Integer getFk_tb_user_id(){
		return fk_tb_user_id;
	}

	public void setFk_tb_user_id(Integer fk_tb_user_id){
		this.fk_tb_user_id = fk_tb_user_id;
	}

	public String getNumber(){
		return number;
	}

	public void setNumber(String number){
		this.number = number;
	}

	public Float getTotal_money(){
		return total_money;
	}

	public void setTotal_money(Float total_money){
		this.total_money = total_money;
	}

	public Float getReal_money(){
		return real_money;
	}

	public void setReal_money(Float real_money){
		this.real_money = real_money;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

	public List<OrderFoodMenu> getOrderFoodMenus() {
		return orderFoodMenus;
	}

	public void setOrderFoodMenus(List<OrderFoodMenu> orderFoodMenus) {
		this.orderFoodMenus = orderFoodMenus;
	}
}
