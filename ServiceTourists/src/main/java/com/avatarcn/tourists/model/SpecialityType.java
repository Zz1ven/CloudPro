package com.avatarcn.tourists.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by z1ven on 2018/1/30 12:32
 */
@JsonIgnoreProperties(value = {"handler"})
public class SpecialityType {
    private int id;
    private String name;
    private String icon;

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
}
