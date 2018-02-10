package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.model.Coupon;
import com.avatarcn.tourists.model.PromotionCoupon;
import com.avatarcn.tourists.service.PromotionCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by z1ven on 2018/2/7 16:39
 */
@Api(value = "/v1/promotion/coupon", description = "优惠活动的优惠券模块")
@RequestMapping(value = "/v1/promotion/coupon")
@RestController
public class PromotionCouponController {

    @Autowired
    private PromotionCouponService promotionCouponService;

    @ApiOperation("添加一个优惠活动的优惠券")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<PromotionCoupon> addPromotionCoupon(@ApiParam(value = "优惠券主键ID", required = true) @RequestParam(value = "coupon_id") Integer coupon_id,
                                                        @ApiParam(value = "优惠活动主键ID", required = true) @RequestParam(value = "promotion_id") Integer promotion_id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, promotionCouponService.insert(promotion_id, coupon_id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("删除指定的优惠活动的优惠券")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean<Integer> deleteById(@ApiParam(value = "主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, promotionCouponService.deleteById(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("获取指定的优惠活动的优惠券")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PromotionCoupon> getPromotionCoupon(@ApiParam(value = "主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, promotionCouponService.selectById(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("根据优惠活动获取所有的优惠券")
    @RequestMapping(value = "/all/{promotion_id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<AllResponse<PromotionCoupon>> getAllPromotionCouponByPromotionId(@ApiParam(value = "活动主键ID", required = true) @PathVariable(value = "promotion_id") Integer promotion_id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, promotionCouponService.selectAllByPromotionId(promotion_id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("修改指定的优惠活动的优惠券")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<PromotionCoupon> updatePromotionCoupon(@ApiParam(value = "主键ID", required = true) @PathVariable(value = "id") Integer id,
                                                           @ApiParam(value = "优惠券主键ID", required = true) @RequestParam(value = "coupon_id") Integer coupon_id,
                                                           @ApiParam(value = "活动主键ID", required = true) @RequestParam(value = "promotion_id") Integer promotion_id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, promotionCouponService.update(id, promotion_id, coupon_id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("领取活动优惠券")
    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<AllResponse<Coupon>> receivePromotionCoupon(@ApiParam(value = "活动ID", required = true) @RequestParam(value = "promotion_id") Integer promotion_id,
                                                                @ApiParam(value = "手机号", required = true) @RequestParam(value = "phone") String phone) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, promotionCouponService.receivePromotionCoupon(promotion_id, phone));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }
}
