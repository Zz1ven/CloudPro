package com.avatarcn.user.controller;

import com.avatarcn.user.exception.ErrorCode;
import com.avatarcn.user.exception.ErrorCodeException;
import com.avatarcn.user.model.Role;
import com.avatarcn.user.response.JsonBean;
import com.avatarcn.user.response.PageResponse;
import com.avatarcn.user.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by z1ven on 2018/2/9 15:16
 */
@Api(value = "/role", description = "角色模块")
@RequestMapping(value = "/role")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("添加一个新的角色")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<Role> addRole(@ApiParam(value = "角色名", required = true) @RequestParam(value = "name") String name) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, roleService.insert(name));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("删除指定的角色")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean<Integer> deleteById(@ApiParam(value = "主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, roleService.deleteById(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("获取指定的角色")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Role> getRoleById(@ApiParam(value = "主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, roleService.selectById(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("分页获取所有的角色")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<Role>> getPage(@ApiParam(value = "从第几个开始") @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                          @ApiParam(value = "每页的个数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return new JsonBean<>(ErrorCode.SUCCESS, roleService.selectPage(offset, pageSize));
    }

    @ApiOperation("修改指定的角色")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<Role> update(@ApiParam(value = "主键ID", required = true) @PathVariable(value = "id") Integer id,
                                 @ApiParam(value = "角色名", required = true) @RequestParam(value = "name") String name) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, roleService.update(id, name));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }
}
