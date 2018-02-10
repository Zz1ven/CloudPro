package com.avatarcn.tourists.model.robotnet;

import java.util.Date;

/**
 * Created by Administrator on 2017/3/9
 */
public class AppRobot {
    private Integer id;
    private String app_id;
    private String robot_number;
    private String name;
    private String remark;
    private Boolean is_open;

    private Date time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getRobot_number() {
        return robot_number;
    }

    public void setRobot_number(String robot_number) {
        this.robot_number = robot_number;
    }

    public Boolean getIs_open() {
        return is_open;
    }

    public void setIs_open(Boolean is_open) {
        this.is_open = is_open;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
