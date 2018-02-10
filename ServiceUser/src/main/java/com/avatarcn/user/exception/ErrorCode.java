package com.avatarcn.user.exception;

/**
 * Created by AutoCode on 2018-1-17
 */
public class ErrorCode{

    public static final ErrorCode SUCCESS = new ErrorCode(0, "成功", true);

    private boolean success;
    private int code;
    private String msg;

    public ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.success = false;
    }

    public ErrorCode(int code, String msg, boolean success) {
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return success;
    }
}
