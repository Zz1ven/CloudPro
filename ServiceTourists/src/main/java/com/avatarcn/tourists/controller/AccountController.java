package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.feign.UserServiceFeign;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.model.user.Account;
import com.avatarcn.tourists.model.user.User;
import com.avatarcn.tourists.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by z1ven on 2018/1/22 09:32
 */
@Api(value = "/v1/account", description = "账号模块")
@RequestMapping(value = "/v1/account")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserServiceFeign userServiceFeign;

    @ApiOperation("添加平台账号")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<Account> insert(@ApiParam(value = "token", required = true) @RequestParam(value = "token") String token) {
        JsonBean<User> userJsonBean = userServiceFeign.getUser(token);
        if (!userJsonBean.isSuccess()) {
            return new JsonBean<>(new ErrorCode(userJsonBean.getError_code(), userJsonBean.getMsg()));
        }
        return new JsonBean<>(ErrorCode.SUCCESS, accountService.insert(userJsonBean.getData().getId()));
    }

    @ApiOperation("根据ID获取账号")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Account> getAccountById(@ApiParam(value = "主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, accountService.selectById(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("根据token获取账号")
    @RequestMapping(value = "/server", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Account> getAccountByServerId(@ApiParam(value = "token", required = true) @RequestParam(value = "token") String token) {
        JsonBean<User> userJsonBean = userServiceFeign.getUser(token);
        if (!userJsonBean.isSuccess()) {
            return new JsonBean<>(new ErrorCode(userJsonBean.getError_code(), userJsonBean.getMsg()));
        }
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, accountService.selectByServerId(userJsonBean.getData().getId()));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }
}
