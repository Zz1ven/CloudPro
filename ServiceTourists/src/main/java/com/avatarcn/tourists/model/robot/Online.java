package com.avatarcn.tourists.model.robot;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/7.
 */
public class Online {
    private Integer id;
    private Integer robot_id;
    private Boolean is_online;
    private Integer version;
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRobot_id() {
        return robot_id;
    }

    public void setRobot_id(Integer robot_id) {
        this.robot_id = robot_id;
    }

    public Boolean getIs_online() {
        return is_online;
    }

    public void setIs_online(Boolean is_online) {
        this.is_online = is_online;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
