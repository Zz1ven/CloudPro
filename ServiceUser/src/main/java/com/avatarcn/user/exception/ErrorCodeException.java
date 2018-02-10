package com.avatarcn.user.exception;

/**
 * 用户自定义异常
 * Created by AutoCode on 2018-1-17
 */
public class ErrorCodeException extends Exception {

    ErrorCode errorCode;
    public static final ErrorCode PARAM_ERROR = new ErrorCode(101, "参数错误");
    public static final ErrorCode DATA_NULL = new ErrorCode(102, "数据不存在");
    public static final ErrorCode DELETE_ERROR = new ErrorCode(103, "删除失败");

    public ErrorCodeException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
    public class DATA_NO_ERROR {
    }
}
