package com.avatarcn.tourists.model.robot;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/7.
 */
public class Memory {
    private Integer id;
    private Integer robot_id;
    private Integer total;
    private Integer used;
    private Integer system_used;
    private Integer photo_used;
    private Integer video_used;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public Integer getSystem_used() {
        return system_used;
    }

    public void setSystem_used(Integer system_used) {
        this.system_used = system_used;
    }

    public Integer getPhoto_used() {
        return photo_used;
    }

    public void setPhoto_used(Integer photo_used) {
        this.photo_used = photo_used;
    }

    public Integer getVideo_used() {
        return video_used;
    }

    public void setVideo_used(Integer video_used) {
        this.video_used = video_used;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
