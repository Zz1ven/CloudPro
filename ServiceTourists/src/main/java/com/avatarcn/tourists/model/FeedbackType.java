package com.avatarcn.tourists.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by z1ven on 2018/1/25 17:40
 */
@JsonIgnoreProperties(value = {"handler"})
public class FeedbackType {
    private int id;
    private String name;

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
}
