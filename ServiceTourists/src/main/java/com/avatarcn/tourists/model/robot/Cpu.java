package com.avatarcn.tourists.model.robot;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/7.
 */
public class Cpu {
    private Integer id;
    private Integer robot_id;
    private Integer cpu;
    private Integer memory;
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

    public Integer getCpu() {
        return cpu;
    }

    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
