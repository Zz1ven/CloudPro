package com.avatarcn.tourists.feign;

import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.user.PasssmsResponse;
import com.avatarcn.tourists.model.user.User;
import com.avatarcn.tourists.model.user.UserInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * Created by MDF on 2017-11-27.
 */
@FeignClient(value = "SERVICE-USER-API")
public interface UserServiceFeign {
    //通过token获取用户
    @RequestMapping(value = "/v1/user/token",method = RequestMethod.GET)
    JsonBean<User> getUser(@RequestParam("token") String token);

    //通过手机号获取用户信息
    @RequestMapping(value = "/v2/user/info/phone",method = RequestMethod.GET)
    JsonBean<UserInfo>  getUserByPhone(@RequestParam("phone") String phone);

//修改用户信息
    @RequestMapping(value = "/v2/user/info", method = RequestMethod.PUT)
    JsonBean<UserInfo> info_edit(@RequestParam(value = "user_id") Integer user_id,
                                 @RequestParam(value = "nickname") String nickname,
                                 @RequestParam(value = "sex") String sex,
                                 @RequestParam(value = "img") String img,
                                 @RequestParam(value = "birthday") Date birthday);

//获取用户信息
    @RequestMapping(value = "/v2/user/info/{id}", method = RequestMethod.GET)
    JsonBean<UserInfo> info_get(@PathVariable(value = "id") Integer id);

    //通过短信验证码创建用户
    @RequestMapping(value = "/v1/user/create_sms",method = RequestMethod.POST)
    JsonBean<PasssmsResponse>  greateUserBycode(@RequestParam("cookie") String cookie, @RequestParam("code") String code,
                                                @RequestParam("phone") String phone,
                                                @RequestParam("password") String password);
    //修改用户信息
    @RequestMapping(value = "/v1/user/info",method = RequestMethod.PUT)
    JsonBean<UserInfo>  updateUserInfos(@RequestParam("token") String token,
                                        @RequestParam("nickname") String nickname,
                                        @RequestParam("sex") String sex,
                                        @RequestParam("img") String img,
                                        @RequestParam("birthday") long birthday);

    //重置密码
    @RequestMapping(value = "/v1/user/reset_password",method = RequestMethod.POST)
    JsonBean  resetpassword(@RequestParam("token") String token, @RequestParam("password") String password);

}
