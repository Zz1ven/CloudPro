package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.model.FeedbackType;
import com.avatarcn.tourists.service.FeedbackTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by z1ven on 2018/1/26 10:35
 */
@Api(value = "/v1/feedback/type", description = "反馈类型模块")
@RequestMapping(value = "/v1/feedback/type")
@RestController
public class FeedbackTypeController {

    @Autowired
    private FeedbackTypeService feedbackTypeService;

    @ApiOperation("添加一个反馈类型")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<FeedbackType> addFeedbackType(@ApiParam(value = "反馈类型名", required = true) @RequestParam(value = "name") String name) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, feedbackTypeService.addFeedbackType(name));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("删除指定的反馈类型")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean<Integer> deleteFeedbackType(@ApiParam(value = "反馈类型主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, feedbackTypeService.deleteFeedbackType(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("获取指定的反馈类型")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<FeedbackType> getFeedbackType(@ApiParam(value = "反馈类型主键ID", required = true) @PathVariable(value = "id") Integer id) {
        return new JsonBean<>(ErrorCode.SUCCESS, feedbackTypeService.selectById(id));
    }

    @ApiOperation("获取所有的反馈类型")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<AllResponse<FeedbackType>> getAllFeedbackType() {
        return new JsonBean<>(ErrorCode.SUCCESS, feedbackTypeService.selectAll());
    }

    @ApiOperation("修改指定的反馈类型")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<FeedbackType> updateFeedbackType(@ApiParam(value = "反馈类型主键ID", required = true) @PathVariable(value = "id") Integer id,
                                                     @ApiParam(value = "反馈类型名", required = true) @RequestParam(value = "name") String name) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, feedbackTypeService.update(id, name));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }
}
