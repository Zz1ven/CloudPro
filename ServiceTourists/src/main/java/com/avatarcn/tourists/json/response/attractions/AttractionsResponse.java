package com.avatarcn.tourists.json.response.attractions;

/**
 * Created by z1ven on 2018/2/2 11:11
 */
public class AttractionsResponse {
    private int id;
    private String name;
    private String image;
    private Integer robot_id;
    private String remark;
    private String robot_code;
    private String robot_number;
    private int power;
    private boolean online;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getRobot_id() {
        return robot_id;
    }

    public void setRobot_id(Integer robot_id) {
        this.robot_id = robot_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRobot_code() {
        return robot_code;
    }

    public void setRobot_code(String robot_code) {
        this.robot_code = robot_code;
    }

    public String getRobot_number() {
        return robot_number;
    }

    public void setRobot_number(String robot_number) {
        this.robot_number = robot_number;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
