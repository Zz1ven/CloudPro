package com.avatarcn.tourists.json.response.active;

import java.util.Date;

/**
 * Created by z1ven on 2018/1/19 13:20
 */
public class ActiveAssessResponse {
    private int id;
    private int user_id;
    private String nickname;
    private String userUrl;
    private int active_id;
    private float score;
    private String assess;
    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
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

    public int getActive_id() {
        return active_id;
    }

    public void setActive_id(int active_id) {
        this.active_id = active_id;
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
