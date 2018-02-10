package com.avatarcn.user.controller;

import com.avatarcn.user.exception.ErrorCode;
import com.avatarcn.user.exception.ErrorCodeException;
import com.avatarcn.user.model.User;
import com.avatarcn.user.response.JsonBean;
import com.avatarcn.user.response.PageResponse;
import com.avatarcn.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by z1ven on 2018/2/9 15:55
 */
@Api(value = "/user", description = "用户模块")
@RequestMapping(value = "/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("添加一个新的用户")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<User> addUser(@ApiParam(value = "用户名", required = true) @RequestParam(value = "username") String username,
                                  @ApiParam(value = "密码", required = true) @RequestParam(value = "password") String password) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, userService.insert(username, password));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("删除指定的用户")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean<Integer> deleteById(@ApiParam(value = "主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, userService.deleteById(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("获取指定的用户")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<User> getUserById(@ApiParam(value = "主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, userService.selectById(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("分页获取所有的角色")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<User>> getPage(@ApiParam(value = "从第几个开始") @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                                @ApiParam(value = "每页的个数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return new JsonBean<>(ErrorCode.SUCCESS, userService.selectPage(offset, pageSize));
    }

    @ApiOperation("修改指定用户的密码")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<User> updatePassword(@ApiParam(value = "主键ID", required = true) @PathVariable(value = "id") Integer id,
                                         @ApiParam(value = "密码", required = true) @RequestParam(value = "password") String password) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, userService.update(id, null, password, null, null));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("锁定指定用户")
    @RequestMapping(value = "/lock/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<User> lockUser(@ApiParam(value = "主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, userService.update(id, null, null, true, null));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("解锁指定用户")
    @RequestMapping(value = "/unlock/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<User> unlockUser(@ApiParam(value = "主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, userService.update(id, null, null, false, null));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }
}
