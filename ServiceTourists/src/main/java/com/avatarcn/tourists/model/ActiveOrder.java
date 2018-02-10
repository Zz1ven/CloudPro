package com.avatarcn.tourists.model;

import com.avatarcn.tourists.json.response.active.ActiveResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by z1ven on 2018/1/19 15:20
 */
@JsonIgnoreProperties(value = {"handler"})
public class ActiveOrder {
    private int id;
    private int fk_tb_active_id;
    private int fk_tb_user_id;
    private int fk_tb_order_status_id;
    private int amount;
    private String number;
    private float total_money;
    private float real_money;
    private String tourist_name;
    private String tourist_phone;
    private Date time;

    private ActiveResponse activeResponse;
    private OrderStatus orderStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_tb_active_id() {
        return fk_tb_active_id;
    }

    public void setFk_tb_active_id(int fk_tb_active_id) {
        this.fk_tb_active_id = fk_tb_active_id;
    }

    public int getFk_tb_user_id() {
        return fk_tb_user_id;
    }

    public void setFk_tb_user_id(int fk_tb_user_id) {
        this.fk_tb_user_id = fk_tb_user_id;
    }

    public int getFk_tb_order_status_id() {
        return fk_tb_order_status_id;
    }

    public void setFk_tb_order_status_id(int fk_tb_order_status_id) {
        this.fk_tb_order_status_id = fk_tb_order_status_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getTotal_money() {
        return total_money;
    }

    public void setTotal_money(float total_money) {
        this.total_money = total_money;
    }

    public float getReal_money() {
        return real_money;
    }

    public void setReal_money(float real_money) {
        this.real_money = real_money;
    }

    public String getTourist_name() {
        return tourist_name;
    }

    public void setTourist_name(String tourist_name) {
        this.tourist_name = tourist_name;
    }

    public String getTourist_phone() {
        return tourist_phone;
    }

    public void setTourist_phone(String tourist_phone) {
        this.tourist_phone = tourist_phone;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public ActiveResponse getActiveResponse() {
        return activeResponse;
    }

    public void setActiveResponse(ActiveResponse activeResponse) {
        this.activeResponse = activeResponse;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
