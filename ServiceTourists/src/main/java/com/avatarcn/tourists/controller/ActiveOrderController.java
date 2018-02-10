package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.feign.UserServiceFeign;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.model.ActiveOrder;
import com.avatarcn.tourists.model.user.User;
import com.avatarcn.tourists.service.ActiveOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by z1ven on 2018/1/19 17:47
 */
@Api(value = "/v1/active/order", description = "活动订单模块")
@RequestMapping(value = "/v1/active/order")
@RestController
public class ActiveOrderController {

    @Autowired
    private ActiveOrderService activeOrderService;

    @Autowired
    private UserServiceFeign userServiceFeign;

    @ApiOperation("添加活动订单")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<ActiveOrder> addActiveOrder(@ApiParam(value = "token", required = true) @RequestParam String token,
                                                @ApiParam(value = "活动ID", required = true) @RequestParam Integer active_id,
                                                @ApiParam(value = "购买数量", required = true) @RequestParam Integer amount,
                                                @ApiParam(value = "订单状态ID", required = true) @RequestParam Integer status_id,
                                                @ApiParam(value = "游客姓名") @RequestParam(value = "tourist_name", required = false) String tourist_name,
                                                @ApiParam(value = "游客手机号") @RequestParam(value = "tourist_phone", required = false) String tourist_phone,
                                                @ApiParam(value = "用户优惠券ID") @RequestParam(value = "coupons_user_id", required = false) Integer coupons_user_id) {
        JsonBean<User> userJsonBean = userServiceFeign.getUser(token);
        if (!userJsonBean.isSuccess()) {
            return new JsonBean<>(new ErrorCode(userJsonBean.getError_code(), userJsonBean.getMsg()));
        }
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, activeOrderService.addActiveOrder(userJsonBean.getData().getId(), active_id, amount, status_id, tourist_name, tourist_phone, coupons_user_id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("删除指定的活动订单")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean<Integer> deleteActiveOrder(@ApiParam(value = "活动订单ID", required = true) @PathVariable Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, activeOrderService.deleteActiveOrder(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("获取指定的活动订单")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<ActiveOrder> getActiveOrder(@ApiParam(value = "活动订单ID", required = true) @PathVariable Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, activeOrderService.getActiveOrder(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("分页获取指定用户的活动订单")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<ActiveOrder>> getPageActiveOrder(@ApiParam(value = "token", required = true) @RequestParam String token,
                                                                  @ApiParam(value = "从第几个开始") @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                                                  @ApiParam(value = "每页的个数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        JsonBean<User> userJsonBean = userServiceFeign.getUser(token);
        if (!userJsonBean.isSuccess()) {
            return new JsonBean<>(new ErrorCode(userJsonBean.getError_code(), userJsonBean.getMsg()));
        }
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, activeOrderService.getPageActiveOrder(userJsonBean.getData().getId(), offset, pageSize));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("更新活动订单")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<ActiveOrder> updateActiveOrder(@ApiParam(value = "活动订单ID", required = true) @PathVariable Integer id,
                                                   @ApiParam(value = "订单状态ID", required = true) @RequestParam Integer status_id,
                                                   @ApiParam(value = "实际总金额", required = true) @RequestParam Float real_money) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, activeOrderService.updateActiveOrder(id, status_id, real_money));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("支付完成后的活动订单处理")
    @RequestMapping(value = "/pay/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<ActiveOrder> payActiveOrder(@ApiParam(value = "活动订单ID", required = true) @PathVariable Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, activeOrderService.payActiveOrder(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }
}
