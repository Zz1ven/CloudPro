package com.avatarcn.tourists.json.response.user;


import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2016/10/21.
 */
public class PasssmsResponse {
    @ApiModelProperty("需要校验的手机号码")
    private String phone;
    @ApiModelProperty("是否填写了初始密码")
    private Boolean is_password;
    @ApiModelProperty("16位用户登陆令牌凭证")
    private String token;
    @ApiModelProperty("加密后的密码")
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIs_password() {
        return is_password;
    }

    public void setIs_password(Boolean is_password) {
        this.is_password = is_password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
