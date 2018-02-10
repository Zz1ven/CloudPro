package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.json.response.FoodTypeResponse;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.model.FoodType;
import com.avatarcn.tourists.service.FoodTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "/v1/food_type", description = "菜系类型模块")
@RequestMapping(value = "/v1/food_type")
@RestController
public class FoodTypeController{
	@Autowired
	private FoodTypeService foodTypeService;

	@ApiOperation(value = "增加FoodType")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<FoodType> Insert(@ApiParam(value = "菜系类型名称",required = true)@RequestParam(value = "name",required = true)String name,
									 @ApiParam(value = "菜系类型图标",required = false)@RequestParam(value = "type_img",required = false)String type_img){
		try{
			return new JsonBean(ErrorCode.SUCCESS, foodTypeService.insert(name,type_img));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "修改指定的FoodType")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<FoodType> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
	                                 @ApiParam(value = "菜系类型名称",required = true)@RequestParam(value = "name",required = true)String name,
	                                 @ApiParam(value = "菜系类型图标",required = false)@RequestParam(value = "type_img",required = false)String type_img){
		try{
			return new JsonBean(ErrorCode.SUCCESS, foodTypeService.update(id,name,type_img));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的FoodType")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<FoodType> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, foodTypeService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "分页列出所有的FoodType")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<FoodType>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
													   @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		return new JsonBean(ErrorCode.SUCCESS, foodTypeService.selectPage(offset,pageSize));
	}

	@ApiOperation(value = "列出所有的FoodType")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<AllResponse<FoodType>> selectAll() {
		return new JsonBean<>(ErrorCode.SUCCESS, foodTypeService.selectAll());
	}

	@ApiOperation(value = "列出所有的FoodType下所有的菜系")
	@RequestMapping(value = "/food/all", method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<AllResponse<FoodTypeResponse>> selectAllFoods() {
		return new JsonBean<>(ErrorCode.SUCCESS, foodTypeService.selectTypeFoods());
	}

	@ApiOperation(value = "删除指定的FoodType")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, foodTypeService.deleteByPrimaryKey(id));
	}

}
