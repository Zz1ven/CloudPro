package com.avatarcn.tourists.json.response.active;

import com.avatarcn.tourists.model.ActiveRemark;

import java.util.Date;
import java.util.List;

/**
 * Created by z1ven on 2018/1/18 16:12
 */
public class ActiveResponse {
    private int id;
    private String name;
    private String icon;
    private String description;
    private Date time;
    private List<ActiveRemark> activeRemarkList;

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
}
