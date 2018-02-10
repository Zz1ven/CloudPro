package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.model.Coupon;
import com.avatarcn.tourists.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "/v1/coupon", description = "优惠券模块")
@RequestMapping(value = "/v1/coupon")
@RestController
public class CouponController{
	@Autowired
	private CouponService couponService;

	@ApiOperation(value = "增加Coupon")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<Coupon> Insert(@ApiParam(value = "优惠卷类型id",required = false)@RequestParam(value = "fk_tb_coupons_type_id",required = false)Integer fk_tb_coupons_type_id,
								   @ApiParam(value = "名称",required = false)@RequestParam(value = "name",required = false)String name,
								   @ApiParam(value = "优惠劵面额",required = false)@RequestParam(value = "money",required = false)Float money,
								   @ApiParam(value = "最低消费金额",required = false)@RequestParam(value = "spend_money",required = false)Float spend_money,
								   @ApiParam(value = "优惠券活动描述",required = false)@RequestParam(value = "explains",required = false)String explains,
								   @ApiParam(value = "有效的开始时间",required = false)@RequestParam(value = "valid_start_time",required = false)long valid_start_time,
								   @ApiParam(value = "有效的结束时间",required = false)@RequestParam(value = "valid_end_time",required = false)long valid_end_time){
		try{
			return new JsonBean(ErrorCode.SUCCESS, couponService.insert(fk_tb_coupons_type_id,name,money,spend_money,explains,valid_start_time,valid_end_time));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "修改指定的Coupon")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<Coupon> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
	                               @ApiParam(value = "优惠卷类型id",required = false)@RequestParam(value = "fk_tb_coupons_type_id",required = false)Integer fk_tb_coupons_type_id,
	                               @ApiParam(value = "名称",required = false)@RequestParam(value = "name",required = false)String name,
	                               @ApiParam(value = "优惠劵面额",required = false)@RequestParam(value = "money",required = false)Float money,
	                               @ApiParam(value = "最低消费金额",required = false)@RequestParam(value = "spend_money",required = false)Float spend_money,
	                               @ApiParam(value = "优惠券活动描述",required = false)@RequestParam(value = "explains",required = false)String explains,
	                               @ApiParam(value = "有效的开始时间",required = false)@RequestParam(value = "valid_start_time",required = false)long valid_start_time,
	                               @ApiParam(value = "有效的结束时间",required = false)@RequestParam(value = "valid_end_time",required = false)long valid_end_time){
		try{
			return new JsonBean(ErrorCode.SUCCESS, couponService.update(id,fk_tb_coupons_type_id,name,money,spend_money,explains,valid_start_time,valid_end_time));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的Coupon")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<Coupon> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, couponService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "列出所有的Coupon")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<Coupon>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
													 @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		try{
			return new JsonBean(ErrorCode.SUCCESS, couponService.selectPage(offset,pageSize));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation("获取所有有效的优惠券")
	@RequestMapping(value = "/page/valid", method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<AllResponse<Coupon>> selectAllValidCoupon() {
		try {
			return new JsonBean<>(ErrorCode.SUCCESS, couponService.selectAllValid());
		} catch (ErrorCodeException e) {
			return new JsonBean<>(e.getErrorCode());
		}
	}

	@ApiOperation(value = "删除指定的Coupon")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, couponService.deleteByPrimaryKey(id));
	}

}
