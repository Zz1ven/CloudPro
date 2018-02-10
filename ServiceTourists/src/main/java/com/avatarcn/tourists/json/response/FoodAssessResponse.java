package com.avatarcn.tourists.json.response;

import java.util.Date;

/**
 * Created by MDF on 2018-1-23.
 */
public class FoodAssessResponse {
    private Integer id;
    private Integer user_id;
    private String nickname;
    private String userUrl;
    private Integer food_order_id;
    private float score;
    private String assess;
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public Integer getFood_order_id() {
        return food_order_id;
    }

    public void setFood_order_id(Integer food_order_id) {
        this.food_order_id = food_order_id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getAssess() {
        return assess;
    }

    public void setAssess(String assess) {
        this.assess = assess;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}



