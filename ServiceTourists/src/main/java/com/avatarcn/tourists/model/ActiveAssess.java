package com.avatarcn.tourists.model;

import java.util.Date;

/**
 * Created by z1ven on 2018/1/19 11:54
 */
public class ActiveAssess {
    private int id;
    private int fk_tb_user_id;
    private int fk_tb_active_id;
    private float score;
    private String assess;
    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_tb_user_id() {
        return fk_tb_user_id;
    }

    public void setFk_tb_user_id(int fk_tb_user_id) {
        this.fk_tb_user_id = fk_tb_user_id;
    }

    public int getFk_tb_active_id() {
        return fk_tb_active_id;
    }

    public void setFk_tb_active_id(int fk_tb_active_id) {
        this.fk_tb_active_id = fk_tb_active_id;
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
