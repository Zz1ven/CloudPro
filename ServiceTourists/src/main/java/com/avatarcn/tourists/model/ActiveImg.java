package com.avatarcn.tourists.model;

import java.util.Date;

/**
 * Created by z1ven on 2018/1/18 14:41
 */
public class ActiveImg {
    private int id;
    private int fk_tb_active_id;
    private String image;
    private Date time;

    public ActiveImg(int fk_tb_active_id, String image, Date time) {
        this.fk_tb_active_id = fk_tb_active_id;
        this.image = image;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_tb_active_id() {
        return fk_tb_active_id;
    }

    public void setFk_tb_active_id(int fk_tb_active_id) {
        this.fk_tb_active_id = fk_tb_active_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
