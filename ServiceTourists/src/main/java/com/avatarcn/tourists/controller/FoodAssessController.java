package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.feign.UserServiceFeign;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.PageFoodAssessResponse;
import com.avatarcn.tourists.model.FoodAssess;
import com.avatarcn.tourists.model.user.User;
import com.avatarcn.tourists.service.FoodAssessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "/v1/food_assess", description = "")
@RequestMapping(value = "/v1/food_assess")
@RestController
public class FoodAssessController{
	@Autowired
	private FoodAssessService foodAssessService;
	@Autowired
	private UserServiceFeign userServiceFeign;

	@ApiOperation(value = "增加FoodAssess")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<FoodAssess> Insert(@ApiParam(value = "用户登录令牌",required = false)@RequestParam(value = "token",required = true)String token,
									   @ApiParam(value = "菜系订单id",required = false)@RequestParam(value = "fk_tb_food_id",required = true)Integer fk_tb_food_order_id,
									   @ApiParam(value = "评分",required = false)@RequestParam(value = "score",required = true)Float score,
									   @ApiParam(value = "评论内容",required = false)@RequestParam(value = "assess",required = true)String assess){
		JsonBean<User> userJsonBean = userServiceFeign.getUser(token);
		if (!userJsonBean.isSuccess()) {
			return new JsonBean<>(new ErrorCode(userJsonBean.getError_code(), userJsonBean.getMsg()));
		}
		try{
			return new JsonBean(ErrorCode.SUCCESS, foodAssessService.insert(userJsonBean.getData().getId(),fk_tb_food_order_id,score,assess));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}

	}

	@ApiOperation(value = "获取指定的FoodAssess")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<FoodAssess> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, foodAssessService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "列出所有的FoodAssess")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageFoodAssessResponse> selectPage(@ApiParam(value = "订单id", required = true) @RequestParam Integer fk_tb_food_order_id,
													   @ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
													   @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		try{
			return new JsonBean(ErrorCode.SUCCESS, foodAssessService.selectPage(fk_tb_food_order_id,offset,pageSize));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "删除指定的FoodAssess")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, foodAssessService.deleteByPrimaryKey(id));
	}

}
