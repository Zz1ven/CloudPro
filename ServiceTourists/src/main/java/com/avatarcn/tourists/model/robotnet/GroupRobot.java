package com.avatarcn.tourists.model.robotnet;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/7.
 */
public class GroupRobot {
    private Integer id;
    private Integer group_id;
    private String robot_number;
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getRobot_number() {
        return robot_number;
    }

    public void setRobot_number(String robot_number) {
        this.robot_number = robot_number;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
