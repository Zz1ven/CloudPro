package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.feign.UserServiceFeign;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.active.ActiveAssessResponse;
import com.avatarcn.tourists.json.response.active.PageActiveAssessResponse;
import com.avatarcn.tourists.model.user.User;
import com.avatarcn.tourists.service.ActiveAssessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by z1ven on 2018/1/20 10:18
 */
@Api(value = "/v1/active/assess", description = "活动评论模块")
@RequestMapping("/v1/active/assess")
@RestController
public class ActiveAssessController {

    @Autowired
    private ActiveAssessService activeAssessService;

    @Autowired
    private UserServiceFeign userServiceFeign;

    @ApiOperation("根据活动订单添加一条活动评论")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<ActiveAssessResponse> addActiveAssess(@ApiParam(value = "token", required = true) @RequestParam String token,
                                                          @ApiParam(value = "活动订单ID", required = true) @RequestParam Integer active_order_id,
                                                          @ApiParam(value = "评分", required = true) @RequestParam float score,
                                                          @ApiParam(value = "评论", required = true) @RequestParam String assess) {
        JsonBean<User> userJsonBean = userServiceFeign.getUser(token);
        if (!userJsonBean.isSuccess()) {
            return new JsonBean<>(new ErrorCode(userJsonBean.getError_code(), userJsonBean.getMsg()));
        }
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, activeAssessService.addActiveAssess(userJsonBean.getData().getId(), active_order_id, score, assess));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("删除指定的活动评论")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean<Integer> deleteActiveAssess(@ApiParam(value = "活动评论ID", required = true) @PathVariable Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, activeAssessService.deleteActiveAssess(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("分页获取指定活动的评论")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageActiveAssessResponse> getPageActiveAssess(@ApiParam(value = "活动ID", required = true) @RequestParam Integer active_id,
                                                                  @ApiParam(value = "从第几个开始") @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                                                  @ApiParam(value = "每页的个数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, activeAssessService.getPageAssess(active_id, offset, pageSize));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }
}
