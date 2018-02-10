package com.avatarcn.tourists.model.user;

import java.util.Date;

/**
 * Created by madengfeng on 2017/4/12.
 */
public class User {
    private Integer id;
    private String username;
    private String password;
    private Date regtime;
    private String phone;
    private Boolean isDisable;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public Boolean getDisable() {
        return isDisable;
    }

    public void setDisable(Boolean isDisable) {
       this.isDisable = isDisable;
    }
}
