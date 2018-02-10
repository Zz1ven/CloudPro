package com.avatarcn.tourists.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by z1ven on 2018/1/26 13:44
 */
@JsonIgnoreProperties(value = {"handler"})
public class FeedbackReply {
    private int id;
    private int fk_tb_feedback_id;
    private String content;
    private Date time;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
