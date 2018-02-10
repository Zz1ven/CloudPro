package com.avatarcn.test.exception;

/**
 * Created by z1ven on 2017/12/20 15:10
 */
public class ErrorCode {

    public static final ErrorCode SUCCESS = new ErrorCode(0, true, "成功");

    private int code;
    private boolean success;
    private String msg;

    public ErrorCode(int code, String msg) {
        this.code = code;
        this.success = false;
        this.msg = msg;
    }

    public ErrorCode(int code, boolean success, String msg) {
        this.code = code;
        this.success = success;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
