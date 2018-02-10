package com.avatarcn.tourists.model;

import java.util.Date;

/**
 * Created by z1ven on 2018/1/17 15:11
 */
public class NewsComment {
    private int id;
    private int fk_tb_user_id;
    private int fk_tb_news_id;
    private String content;
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

    public int getFk_tb_news_id() {
        return fk_tb_news_id;
    }

    public void setFk_tb_news_id(int fk_tb_news_id) {
        this.fk_tb_news_id = fk_tb_news_id;
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
