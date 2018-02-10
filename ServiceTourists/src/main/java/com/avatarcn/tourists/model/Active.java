package com.avatarcn.tourists.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by z1ven on 2018/1/18 13:23
 * 活动
 */
@JsonIgnoreProperties(value = {"handler"})
public class Active {
    private int id;
    private String name;
    private String icon;
    private Time start_time;
    private Time end_time;
    private float price;
    private String description;
    private Date time;

    private List<ActiveRemark> activeRemarkList;
    private List<String> imageList;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<ActiveRemark> getActiveRemarkList() {
        return activeRemarkList;
    }

    public void setActiveRemarkList(List<ActiveRemark> activeRemarkList) {
        this.activeRemarkList = activeRemarkList;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
}
