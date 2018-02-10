package com.avatarcn.tourists.json.body;

/**
 * Created by z1ven on 2018/1/17 15:01
 */
public class PostNews {
    private String title;
    private String content;
    private String img_url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
