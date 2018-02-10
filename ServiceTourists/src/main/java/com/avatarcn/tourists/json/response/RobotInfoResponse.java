package com.avatarcn.tourists.json.response;

import com.avatarcn.tourists.model.robot.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by WKL on 2017-3-18.
 */

@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class RobotInfoResponse {
    private Robot robot;
    private Online online;
    private Memory memory;
    private Type type;
    private Power power;
    private Cpu cpu;
    private Location location;


    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Online getOnline() {
        return online;
    }

    public void setOnline(Online online) {
        this.online = online;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
