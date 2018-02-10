package com.avatarcn.tourists.model.robot;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/3.
 */
public class Robot {
    private Integer id;
    private String robot_code;
    private String robot_number;
    private Boolean activate;
    private Date acttime;
    private Boolean is_disable;
    private String type_code;
    private String bluemac;
    private String wifimac;

    public String getType_code() {
        return type_code;
    }

    public void setType_code(String type_code) {
        this.type_code = type_code;
    }

    public Robot(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRobot_code() {
        return robot_code;
    }

    public void setRobot_code(String robot_code) {
        this.robot_code = robot_code;
    }

    public Boolean getActivate() {
        return activate;
    }

    public void setActivate(Boolean activate) {
        this.activate = activate;
    }

    public Date getActtime() {
        return acttime;
    }

    public void setActtime(Date acttime) {
        this.acttime = acttime;
    }

    public Boolean getIs_disable() {
        return is_disable;
    }

    public void setIs_disable(Boolean is_disable) {
        this.is_disable = is_disable;
    }

    public String getRobot_number() {
        return robot_number;
    }

    public void setRobot_number(String robot_number) {
        this.robot_number = robot_number;
    }

    public String getBluemac() {
        return bluemac;
    }

    public void setBluemac(String bluemac) {
        this.bluemac = bluemac;
    }

    public String getWifimac() {
        return wifimac;
    }

    public void setWifimac(String wifimac) {
        this.wifimac = wifimac;
    }
}
