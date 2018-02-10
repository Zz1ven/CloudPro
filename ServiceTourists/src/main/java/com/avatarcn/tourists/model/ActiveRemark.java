package com.avatarcn.tourists.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by z1ven on 2018/1/18 13:32
 * 活动标签
 */
@JsonIgnoreProperties(value = {"handler"})
public class ActiveRemark {
    private int id;
    private int fk_tb_remark_id;
    private int fk_tb_active_id;

    private String remark_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_tb_remark_id() {
        return fk_tb_remark_id;
    }

    public void setFk_tb_remark_id(int fk_tb_remark_id) {
        this.fk_tb_remark_id = fk_tb_remark_id;
    }

    public int getFk_tb_active_id() {
        return fk_tb_active_id;
    }

    public void setFk_tb_active_id(int fk_tb_active_id) {
        this.fk_tb_active_id = fk_tb_active_id;
    }

    public String getRemark_name() {
        return remark_name;
    }

    public void setRemark_name(String remark_name) {
        this.remark_name = remark_name;
    }
}
