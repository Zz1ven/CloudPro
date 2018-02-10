package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.model.CouponsStatus;
import com.avatarcn.tourists.service.CouponsStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "/v1/coupons_status", description = "优惠卷状态")
@RequestMapping(value = "/v1/coupons_status")
@RestController
public class CouponsStatusController{
	@Autowired
	private CouponsStatusService couponsStatusService;

	@ApiOperation(value = "增加CouponsStatus")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<CouponsStatus> Insert(@ApiParam(value = "",required = false)@RequestParam(value = "status_number",required = false)String status_number,
										  @ApiParam(value = "",required = false)@RequestParam(value = "description",required = false)String description){
		return new JsonBean(ErrorCode.SUCCESS, couponsStatusService.insert(status_number,description));
	}

	@ApiOperation(value = "修改指定的CouponsStatus")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<CouponsStatus> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
	                                      @ApiParam(value = "",required = false)@RequestParam(value = "status_number",required = false)String status_number,
	                                      @ApiParam(value = "",required = false)@RequestParam(value = "description",required = false)String description){
		try{
			return new JsonBean(ErrorCode.SUCCESS, couponsStatusService.update(id,status_number,description));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的CouponsStatus")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<CouponsStatus> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, couponsStatusService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "列出所有的CouponsStatus")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<AllResponse<CouponsStatus>> selectAll() {
		return new JsonBean<>(ErrorCode.SUCCESS, couponsStatusService.selectAll());
	}

	@ApiOperation(value = "删除指定的CouponsStatus")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, couponsStatusService.deleteByPrimaryKey(id));
	}

}
