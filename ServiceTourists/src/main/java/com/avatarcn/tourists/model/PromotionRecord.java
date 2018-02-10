package com.avatarcn.tourists.model;

import java.util.Date;

/**
 * Created by z1ven on 2018/2/8 09:09
 */
public class PromotionRecord {
    private int id;
    private int fk_tb_promotion_id;
    private String phone;
    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_tb_promotion_id() {
        return fk_tb_promotion_id;
    }

    public void setFk_tb_promotion_id(int fk_tb_promotion_id) {
        this.fk_tb_promotion_id = fk_tb_promotion_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
