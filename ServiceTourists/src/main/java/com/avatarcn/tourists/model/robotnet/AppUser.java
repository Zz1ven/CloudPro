package com.avatarcn.tourists.model.robotnet;

import java.util.Date;

/**
 * Created by Administrator on 2017/3/9
 */
public class AppUser {
    private Integer id;
    private String app_id;
    private String user;
    private Date time;

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
