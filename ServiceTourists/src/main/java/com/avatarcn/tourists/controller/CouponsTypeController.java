package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.model.CouponsType;
import com.avatarcn.tourists.service.CouponsTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "/v1/coupons_type", description = "优惠券类型模块")
@RequestMapping(value = "/v1/coupons_type")
@RestController
public class CouponsTypeController{
	@Autowired
	private CouponsTypeService couponsTypeService;

	@ApiOperation(value = "增加CouponsType")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<CouponsType> Insert(@ApiParam(value = "类型编码",required = false)@RequestParam(value = "code",required = false)String code,
										@ApiParam(value = "备注",required = false)@RequestParam(value = "remark",required = false)String remark){
		return new JsonBean(ErrorCode.SUCCESS, couponsTypeService.insert(code,remark));
	}

	@ApiOperation(value = "修改指定的CouponsType")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<CouponsType> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
	                                    @ApiParam(value = "类型编码",required = false)@RequestParam(value = "code",required = false)String code,
	                                    @ApiParam(value = "备注",required = false)@RequestParam(value = "remark",required = false)String remark){
		try{
			return new JsonBean(ErrorCode.SUCCESS, couponsTypeService.update(id,code,remark));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的CouponsType")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<CouponsType> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, couponsTypeService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "列出所有的CouponsType")
	@RequestMapping(value = "/all",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<AllResponse<CouponsType>> selectAll(){
		return new JsonBean<>(ErrorCode.SUCCESS, couponsTypeService.selectAll());
	}

	@ApiOperation(value = "删除指定的CouponsType")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, couponsTypeService.deleteByPrimaryKey(id));
	}

}
