package com.avatarcn.tourists.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by z1ven on 2018/1/25 17:41
 */
@JsonIgnoreProperties(value = {"handler"})
public class FeedbackImg {
    private int id;
    private int fk_tb_feedback_id;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_tb_feedback_id() {
        return fk_tb_feedback_id;
    }

    public void setFk_tb_feedback_id(int fk_tb_feedback_id) {
        this.fk_tb_feedback_id = fk_tb_feedback_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
