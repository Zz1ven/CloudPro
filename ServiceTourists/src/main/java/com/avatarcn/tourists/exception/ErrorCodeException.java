package com.avatarcn.tourists.exception;

/**
 * 用户自定义异常
 * Created by AutoCode on 2018-1-17
 */
public class ErrorCodeException extends Exception{

    ErrorCode errorCode;
    public static final ErrorCode PARAM_ERROR = new ErrorCode(101, "参数错误");
    public static final ErrorCode DATA_NO_ERROR = new ErrorCode(102, "数据不存在");
    public static final ErrorCode DELETE_NO = new ErrorCode(103, "删除失败");
    public static final ErrorCode DELETE_COVER_NO = new ErrorCode(104, "OSS删除图片错误");
    public static final ErrorCode DELETE_FILE_NO = new ErrorCode(105, "OSS删除文件错误");
    public static final ErrorCode COPE_COVER_NO = new ErrorCode(106, "OSS移动图片错误");
    public static final ErrorCode COPE_FILE_NO = new ErrorCode(107, "OSS移动文件错误");
    public static final ErrorCode UP_IN = new ErrorCode(108, "已经点赞");
    public static final ErrorCode FILE_NULL = new ErrorCode(109, "OSS文件不存在");
    public static final  ErrorCode  TIME_PARAM_ERROR= new ErrorCode(110,"时间格式不正确");
    public ErrorCodeException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
    public class DATA_NO_ERROR {
    }
}
