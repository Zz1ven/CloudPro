package com.avatarcn.tourists.model.user;

/**
 * Created by z1ven on 2018/1/18 11:30
 */
public class Account {
    private int id;
    private String server_id;
    private int visible;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServer_id() {
        return server_id;
    }

    public void setServer_id(String server_id) {
        this.server_id = server_id;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }
}
