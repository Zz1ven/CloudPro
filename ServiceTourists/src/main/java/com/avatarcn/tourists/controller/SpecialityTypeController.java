package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.model.SpecialityType;
import com.avatarcn.tourists.service.SpecialityTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by z1ven on 2018/1/30 13:49
 */
@Api(value = "/v1/speciality/type", description = "特产类型模块")
@RequestMapping(value = "/v1/speciality/type")
@RestController
public class SpecialityTypeController {

    @Autowired
    private SpecialityTypeService specialityTypeService;

    @ApiOperation("添加一个特产类型")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<SpecialityType> addSpecialityType(@ApiParam(value = "特产类型名称", required = true) @RequestParam(value = "name") String name,
                                                      @ApiParam(value = "类型图标") @RequestParam(value = "icon", required = false) String icon) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, specialityTypeService.insert(name, icon));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("删除指定的特产类型")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean<Integer> deleteById(@ApiParam(value = "类型主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, specialityTypeService.deleteById(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("获取指定的特产类型")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<SpecialityType> getSpecialityTypeById(@ApiParam(value = "类型主键ID", required = true) @PathVariable(value = "id") Integer id) {
        return new JsonBean<>(ErrorCode.SUCCESS, specialityTypeService.selectById(id));
    }

    @ApiOperation("获取所有的特产类型")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<AllResponse<SpecialityType>> getAllSpecialityType() {
        return new JsonBean<>(ErrorCode.SUCCESS, specialityTypeService.selectByAll());
    }

    @ApiOperation("修改指定的特产类型")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<SpecialityType> updateSpecialityType(@ApiParam(value = "类型主键ID", required = true) @PathVariable(value = "id") Integer id,
                                                         @ApiParam(value = "特产类型名称", required = true) @RequestParam(value = "name") String name,
                                                         @ApiParam(value = "类型图标") @RequestParam(value = "icon", required = false) String icon) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, specialityTypeService.update(id, name, icon));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }
}
