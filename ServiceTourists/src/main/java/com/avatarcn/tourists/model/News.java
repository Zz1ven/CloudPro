package com.avatarcn.tourists.model;

import java.util.Date;

/**
 * Created by z1ven on 2018/1/17 14:14
 */
public class News {
    private int id;
    private String title;
    private String img;
    private String content;
    private int read_count;
    private String web_content;
    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRead_count() {
        return read_count;
    }

    public void setRead_count(int read_count) {
        this.read_count = read_count;
    }

    public String getWeb_content() {
        return web_content;
    }

    public void setWeb_content(String web_content) {
        this.web_content = web_content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
