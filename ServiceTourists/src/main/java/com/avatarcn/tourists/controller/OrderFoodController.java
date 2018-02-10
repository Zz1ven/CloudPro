package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.feign.UserServiceFeign;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.model.FoodMenu;
import com.avatarcn.tourists.model.OrderFood;
import com.avatarcn.tourists.model.user.User;
import com.avatarcn.tourists.service.OrderFoodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "/v1/order_food", description = "用户菜系订单模块")
@RequestMapping(value = "/v1/order_food")
@RestController
public class OrderFoodController{
	@Autowired
	private OrderFoodService orderFoodService;
	@Autowired
	private UserServiceFeign userServiceFeign;

	@ApiOperation(value = "增加订单")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<OrderFood> Insert(@ApiParam(value = "房间id",required = true)@RequestParam(value = "fk_tb_room_id",required = false)Integer fk_tb_room_id,
									  @ApiParam(value = "时间段id",required = true)@RequestParam(value = "fk_tb_time_id",required = false)Integer fk_tb_time_id,
									  @ApiParam(value = "菜系ID和数量的对象", required = true) @RequestBody List<FoodMenu> foodMenuList,
									  @ApiParam(value = "订单状态id",required = true)@RequestParam(value = "fk_tb_order_status_id",required = false)Integer fk_tb_order_status_id,
									  @ApiParam(value = "token", required = true) @RequestParam String token,
									  @ApiParam(value = "预约日期", required = true) @RequestParam(value = "reserve_date") Long reserve_date,
									  @ApiParam(value = "用户优惠券ID") @RequestParam(value = "coupons_user_id", required = false) Integer coupons_user_id) {
		JsonBean<User> userJsonBean = userServiceFeign.getUser(token);
		if (!userJsonBean.isSuccess()) {
			return new JsonBean<>(new ErrorCode(userJsonBean.getError_code(), userJsonBean.getMsg()));
		}
		try{
			return new JsonBean<>(ErrorCode.SUCCESS, orderFoodService.insert(fk_tb_room_id,fk_tb_time_id,fk_tb_order_status_id,userJsonBean.getData().getId(),foodMenuList, reserve_date, coupons_user_id));
		}catch(ErrorCodeException e){
			return new JsonBean<>(e.getErrorCode());
		}
    }

	@ApiOperation(value = "修改指定的OrderFood")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<OrderFood> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
	                                  @ApiParam(value = "",required = false)@RequestParam(value = "fk_tb_order_status_id",required = false)Integer fk_tb_order_status_id,
	                                  @ApiParam(value = "",required = false)@RequestParam(value = "real_money",required = false)Float real_money){
		try{
			return new JsonBean<>(ErrorCode.SUCCESS, orderFoodService.update(id,fk_tb_order_status_id,real_money));
		}catch(ErrorCodeException e){
			return new JsonBean<>(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的OrderFood")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<OrderFood> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean<>(ErrorCode.SUCCESS, orderFoodService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean<>(e.getErrorCode());
		}
	}

	@ApiOperation(value = "列出所有的OrderFood")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<OrderFood>> selectPage(@ApiParam(value = "token", required = true) @RequestParam String token,
														@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
														@ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		JsonBean<User> userJsonBean = userServiceFeign.getUser(token);
		if (!userJsonBean.isSuccess()) {
			return new JsonBean<>(new ErrorCode(userJsonBean.getError_code(), userJsonBean.getMsg()));
		}
		try{
			return new JsonBean<>(ErrorCode.SUCCESS, orderFoodService.selectPage(userJsonBean.getData().getId(),offset,pageSize));
		}catch(ErrorCodeException e){
			return new JsonBean<>(e.getErrorCode());
		}
	}

	@ApiOperation(value = "删除指定的OrderFood")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, orderFoodService.deleteByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation("支付完成后的菜系订单处理")
	@RequestMapping(value = "/pay/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<OrderFood> payFoodOrder(@ApiParam(value = "菜系订单ID", required = true) @PathVariable Integer id) {
		try {
			return new JsonBean<>(ErrorCode.SUCCESS, orderFoodService.payFoodOrder(id));
		} catch (ErrorCodeException e) {
			return new JsonBean<>(e.getErrorCode());
		}
	}

}
