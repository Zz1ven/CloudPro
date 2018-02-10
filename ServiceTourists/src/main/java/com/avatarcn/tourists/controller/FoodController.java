package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.FoodResponse;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.model.Food;
import com.avatarcn.tourists.service.FoodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "/v1/food", description = "菜系模块")
@RequestMapping(value = "/v1/food")
@RestController
public class FoodController{
	@Autowired
	private FoodService foodService;

	@ApiOperation(value = "增加菜系")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<Food> Insert(@ApiParam(value = "菜系类型id",required = false)@RequestParam(value = "fk_tb_food_type_id",required = true)Integer fk_tb_food_type_id,
								 @ApiParam(value = "菜系名称",required = false)@RequestParam(value = "name",required = true)String name,
								 @ApiParam(value = "菜系描述",required = false)@RequestParam(value = "description",required = false)String description,
								 @ApiParam(value = "价格",required = false)@RequestParam(value = "price",required = true)Float price,
								 @ApiParam(value = "折扣",required = false)@RequestParam(value = "rebate_id",required = false)Float  rebate_id,
								 @ApiParam(value = "菜系图片",required = false)@RequestParam(value = "img",required = false)String img){
		try{
			return new JsonBean(ErrorCode.SUCCESS, foodService.insert(fk_tb_food_type_id,name,description,price,rebate_id,img));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "修改指定的菜系")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<Food> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
	                             @ApiParam(value = "菜系类型id",required = false)@RequestParam(value = "fk_tb_food_type_id",required = true)Integer fk_tb_food_type_id,
	                             @ApiParam(value = "菜系名称",required = false)@RequestParam(value = "name",required = true)String name,
	                             @ApiParam(value = "菜系描述",required = false)@RequestParam(value = "description",required = false)String description,
	                             @ApiParam(value = "价格",required = false)@RequestParam(value = "price",required = true)Float price,
	                             @ApiParam(value = "折扣",required = false)@RequestParam(value = "rebate_id",required = false)Float rebate_id,
	                             @ApiParam(value = "菜系图片",required = false)@RequestParam(value = "img",required = false)String img){
		try{
			return new JsonBean(ErrorCode.SUCCESS, foodService.update(id,fk_tb_food_type_id,name,description,price,rebate_id,img));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的Food")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<Food> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, foodService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "根据菜系类型列出所有的Food")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<FoodResponse>> selectPage(@ApiParam(value = "菜系类型id",required = false)@RequestParam(value = "fk_tb_food_type_id",required = true)Integer fk_tb_food_type_id,
														   @ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
														   @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		try{
			return new JsonBean(ErrorCode.SUCCESS, foodService.selectPage(fk_tb_food_type_id,offset,pageSize));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "删除指定的Food")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, foodService.deleteByPrimaryKey(id));
	}

}
