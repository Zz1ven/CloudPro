package com.avatarcn.user.exception;

/**
 * Created by z1ven on 2018/2/9 10:36
 */
public class UserErrorCode extends ErrorCode {

    public static final UserErrorCode ROLE_NULL = new UserErrorCode(201, "角色不存在");
    public static final UserErrorCode ROLE_REPEAT = new UserErrorCode(202, "角色名已存在");
    public static final UserErrorCode USER_NULL = new UserErrorCode(203, "用户不存在");
    public static final UserErrorCode USER_REPEAT = new UserErrorCode(204, "用户名已存在");
    public static final UserErrorCode USER_ERROR = new UserErrorCode(205, "用户名或密码错误");
    public static final UserErrorCode USER_ROLE_NULL = new UserErrorCode(204, "用户角色不存在");
    public static final UserErrorCode USER_ROLE_REPEAT = new UserErrorCode(206, "用户角色重复");

    public UserErrorCode(int code, String msg) {
        super(code, msg);
    }
}
