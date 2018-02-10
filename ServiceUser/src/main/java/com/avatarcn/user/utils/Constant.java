package com.avatarcn.user.utils;

/**
 * Created by z1ven on 2017/10/11 14:26
 */
public class Constant {
    //redis存储的session和凭证
    public static final String SESSIONIDS = "sessionIds";
    public static final String PRINCIPALS = "principals";

    //remember-me相关的常量
    public static String TOKEN_KEY = "cloud_token_key";//token key
    public static String COOKIE_NAME = "cloud_cookie_name";//cookie key
    public static String REMEMBER_PARAMETER = "rememberMe";//客户端remember-me参数名
    public static String REDIRECT_PARAMETER = "redirect";//客户端redirect参数名

    //默认用户注册时的参数值
    public static boolean USER_LOCKED = false;
    public static boolean USER_ENABLE = true;
}
