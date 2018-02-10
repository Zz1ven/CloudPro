package com.avatarcn.user.controller;

import com.avatarcn.user.exception.ErrorCode;
import com.avatarcn.user.exception.ErrorCodeException;
import com.avatarcn.user.model.UserRole;
import com.avatarcn.user.response.JsonBean;
import com.avatarcn.user.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by z1ven on 2018/2/9 15:55
 */
@Api(value = "/user/role", description = "用户角色模块")
@RequestMapping(value = "/user/role")
@RestController
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation("给指定用户添加指定角色")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<UserRole> addUserRole(@ApiParam(value = "用户ID", required = true) @RequestParam(value = "user_id") Integer user_id,
                                          @ApiParam(value = "角色ID", required = true) @RequestParam(value = "role_id") Integer role_id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, userRoleService.insert(user_id, role_id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("删除指定的用户角色")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean<Integer> deleteById(@ApiParam(value = "主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, userRoleService.deleteById(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("修改指定的用户角色")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<UserRole> update(@ApiParam(value = "主键ID", required = true) @PathVariable(value = "id") Integer id,
                                     @ApiParam(value = "用户ID", required = true) @RequestParam(value = "user_id") Integer user_id,
                                     @ApiParam(value = "角色ID", required = true) @RequestParam(value = "role_id") Integer role_id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, userRoleService.update(id, user_id, role_id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }
}
