package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.model.Remark;
import com.avatarcn.tourists.service.RemarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by z1ven on 2018/1/20 10:16
 */
@Api(value = "/v1/remark", description = "标签模块")
@RequestMapping(value = "/v1/remark")
@RestController
public class RemarkController {

    @Autowired
    private RemarkService remarkService;

    @ApiOperation("添加一个新的标签")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<Remark> addRemark(@ApiParam(value = "标签名", required = true) @RequestParam String name) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, remarkService.addRemark(name));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("删除指定的标签")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean<Integer> deleteRemark(@ApiParam(value = "标签ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, remarkService.deleteById(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("获取指定的标签")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Remark> getRemarkById(@ApiParam(value = "标签ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, remarkService.selectById(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation(("获取所有的标签"))
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<AllResponse<Remark>> getAllRemark() {
        return new JsonBean<>(ErrorCode.SUCCESS, remarkService.selectAll());
    }

    @ApiOperation("修改标签")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<Remark> editRemark(@ApiParam(value = "标签ID", required = true) @PathVariable(value = "id") Integer id,
                                       @ApiParam(value = "标签名", required = true) @RequestParam(value = "name") String name) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, remarkService.update(id, name));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }
}
