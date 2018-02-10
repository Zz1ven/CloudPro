package com.avatarcn.tourists.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by z1ven on 2018/2/7 16:13
 */
@JsonIgnoreProperties(value = {"handler"})
public class PromotionCoupon {
    private int id;
    private int fk_tb_promotion_id;
    private int fk_tb_coupon_id;

    private Coupon coupon;

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

    public int getFk_tb_coupon_id() {
        return fk_tb_coupon_id;
    }

    public void setFk_tb_coupon_id(int fk_tb_coupon_id) {
        this.fk_tb_coupon_id = fk_tb_coupon_id;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }
}
