package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.feign.UserServiceFeign;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.model.CouponsUsers;
import com.avatarcn.tourists.model.user.User;
import com.avatarcn.tourists.service.CouponsUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "/v1/coupons_users", description = "用户优惠券模块")
@RequestMapping(value = "/v1/coupons_users")
@RestController
public class CouponsUsersController{
	@Autowired
	private CouponsUsersService couponsUsersService;
	@Autowired
	UserServiceFeign userServiceFeign;

	@ApiOperation(value = "用户获得优惠劵")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<CouponsUsers> Insert(@ApiParam(value = "活动订单",required = false)@RequestParam(value = "fk_tb_order_active_id",required = false)Integer fk_tb_order_active_id,
										 @ApiParam(value = "优惠券状态",required = false)@RequestParam(value = "fk_tb_coupons_status_id",required = true)Integer fk_tb_coupons_status_id,
										 @ApiParam(value = "用户登录令牌",required = false)@RequestParam(value = "token",required = true)String token,
										 @ApiParam(value = "优惠券id",required = false)@RequestParam(value = "fk_tb_coupon_id",required = true)Integer fk_tb_coupon_id,
										 @ApiParam(value = "菜品订单",required = false)@RequestParam(value = "fk_tb_order_food_id",required = false)Integer fk_tb_order_food_id){
		JsonBean<User> jsonBean=userServiceFeign.getUser(token);
		if (!jsonBean.isSuccess())
			return new JsonBean(new ErrorCode(jsonBean.getError_code(),jsonBean.getMsg()));
		try{
			return new JsonBean(ErrorCode.SUCCESS, couponsUsersService.insert(fk_tb_order_active_id,fk_tb_coupons_status_id,jsonBean.getData().getId(),fk_tb_coupon_id,fk_tb_order_food_id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获得用户下有效的优惠劵")
	@RequestMapping(value = "/user/coupons",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<AllResponse<CouponsUsers>> get_user_coupons(@ApiParam(value = "用户登录令牌",required = false)@RequestParam(value = "token",required = false)String token){
		JsonBean<User> jsonBean=userServiceFeign.getUser(token);
		if (!jsonBean.isSuccess())
			return new JsonBean<>(new ErrorCode(jsonBean.getError_code(),jsonBean.getMsg()));

		try{
			return new JsonBean<>(ErrorCode.SUCCESS, couponsUsersService.selectByUser(jsonBean.getData().getId()));
		}catch(ErrorCodeException e){
			return new JsonBean<>(e.getErrorCode());
		}
	}


	@ApiOperation(value = "获得用户下失效的优惠劵")
	@RequestMapping(value = "/user/coupons/invalid",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<AllResponse<CouponsUsers>> get_user_coupons_invalid(@ApiParam(value = "用户登录令牌",required = false)@RequestParam(value = "token",required = false)String token){
		JsonBean<User> jsonBean=userServiceFeign.getUser(token);
		if (!jsonBean.isSuccess())
			return new JsonBean<>(new ErrorCode(jsonBean.getError_code(),jsonBean.getMsg()));

		try{
			return new JsonBean<>(ErrorCode.SUCCESS, couponsUsersService.selectByUserUnvalid(jsonBean.getData().getId()));
		}catch(ErrorCodeException e){
			return new JsonBean<>(e.getErrorCode());
		}
	}

	@ApiOperation("根据创建的订单信息获取可用的用户优惠券")
	@RequestMapping(value = "/user/coupons/order", method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<AllResponse<CouponsUsers>> getUserCouponsByOrderInfo(@ApiParam(value = "token", required = true) @RequestParam(value = "token") String token,
																		 @ApiParam(value = "优惠券类型", required = true) @RequestParam(value = "coupon_type_id") Integer coupon_type_id,
																		 @ApiParam(value = "订单金额", required = true) @RequestParam(value = "money") float money) {
		JsonBean<User> userJsonBean = userServiceFeign.getUser(token);
		if (!userJsonBean.isSuccess()) {
			return new JsonBean<>(new ErrorCode(userJsonBean.getError_code(), userJsonBean.getMsg()));
		}
		try {
			return new JsonBean<>(ErrorCode.SUCCESS, couponsUsersService.selectUserCouponsByOrderInfo(userJsonBean.getData().getId(), coupon_type_id, money));
		} catch (ErrorCodeException e) {
			return new JsonBean<>(e.getErrorCode());
		}
	}

	@ApiOperation(value = "修改指定的CouponsUsers")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<CouponsUsers> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
	                                     @ApiParam(value = "",required = false)@RequestParam(value = "fk_tb_order_active_id",required = false)Integer fk_tb_order_active_id,
	                                     @ApiParam(value = "",required = false)@RequestParam(value = "fk_tb_coupons_status_id",required = false)Integer fk_tb_coupons_status_id,
	                                     @ApiParam(value = "",required = false)@RequestParam(value = "fk_tb_user_id",required = false)Integer fk_tb_user_id,
	                                     @ApiParam(value = "",required = false)@RequestParam(value = "fk_tb_coupon_id",required = false)Integer fk_tb_coupon_id,
	                                     @ApiParam(value = "",required = false)@RequestParam(value = "fk_tb_order_food_id",required = false)Integer fk_tb_order_food_id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, couponsUsersService.update(id,fk_tb_order_active_id,fk_tb_coupons_status_id,fk_tb_user_id,fk_tb_coupon_id,fk_tb_order_food_id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的CouponsUsers")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<CouponsUsers> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, couponsUsersService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "列出所有的CouponsUsers")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<CouponsUsers>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
																  @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		return new JsonBean<>(ErrorCode.SUCCESS, couponsUsersService.selectPage(offset,pageSize));
	}

	@ApiOperation(value = "删除指定的CouponsUsers")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, couponsUsersService.deleteByPrimaryKey(id));
	}

	/*@ApiOperation("活动订单使用指定的用户优惠券")
	@RequestMapping(value = "/active/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<CouponsUsers> activeUseCoupons(@ApiParam(value = "用户优惠券主键ID", required = true) @PathVariable(value = "id") Integer id,
												   @ApiParam(value = "token", required = true) @RequestParam(value = "token") String token,
												   @ApiParam(value = "活动订单ID", required = true) @RequestParam(value = "active_order_id") Integer active_order_id) {
		JsonBean<User> userJsonBean = userServiceFeign.getUser(token);
		if (!userJsonBean.isSuccess()) {
			return new JsonBean<>(new ErrorCode(userJsonBean.getError_code(), userJsonBean.getMsg()));
		}
		try {
			return new JsonBean<>(ErrorCode.SUCCESS, couponsUsersService.activeUseCoupons(id, userJsonBean.getData().getId(), active_order_id));
		} catch (ErrorCodeException e) {
			return new JsonBean<>(e.getErrorCode());
		}
	}

	@ApiOperation("菜系订单使用指定的用户优惠券")
	@RequestMapping(value = "/food/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<CouponsUsers> foodUseCoupons(@ApiParam(value = "用户优惠券主键ID", required = true) @PathVariable(value = "id") Integer id,
												   @ApiParam(value = "token", required = true) @RequestParam(value = "token") String token,
												   @ApiParam(value = "菜系订单ID", required = true) @RequestParam(value = "food_order_id") Integer food_order_id) {
		JsonBean<User> userJsonBean = userServiceFeign.getUser(token);
		if (!userJsonBean.isSuccess()) {
			return new JsonBean<>(new ErrorCode(userJsonBean.getError_code(), userJsonBean.getMsg()));
		}
		try {
			return new JsonBean<>(ErrorCode.SUCCESS, couponsUsersService.foodUseCoupons(id, userJsonBean.getData().getId(), food_order_id));
		} catch (ErrorCodeException e) {
			return new JsonBean<>(e.getErrorCode());
		}
	}*/
}
