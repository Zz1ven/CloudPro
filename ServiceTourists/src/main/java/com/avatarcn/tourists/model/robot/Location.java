package com.avatarcn.tourists.model.robot;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/7.
 */
public class Location {
    private Integer id;
    private Integer robot_id;
    private Double longitude;
    private Double latitude;
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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
