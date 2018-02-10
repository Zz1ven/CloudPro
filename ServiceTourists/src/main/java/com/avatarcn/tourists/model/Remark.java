package com.avatarcn.tourists.model;

import java.util.Date;

/**
 * Created by z1ven on 2018/1/18 13:29
 * 标签
 */
public class Remark {
    private int id;
    private String name;
    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
