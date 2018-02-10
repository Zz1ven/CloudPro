package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.model.Timestamp;
import com.avatarcn.tourists.service.TimestampService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "/v1/timestamp", description = "预定房间时间段模块")
@RequestMapping(value = "/v1/timestamp")
@RestController
public class TimestampController{
	@Autowired
	private TimestampService timestampService;

	@ApiOperation(value = "增加订餐时间段")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<Timestamp> insert(@ApiParam(value = "HH:mm:ss", required = true) @RequestParam(value = "start_time", required = true)String start_time,
									  @ApiParam(value = "HH:mm:ss", required = true) @RequestParam(value = "end_time", required = true) String end_time){
		try {
			return new JsonBean(ErrorCode.SUCCESS, timestampService.insert(start_time,end_time));
		} catch (ErrorCodeException e) {
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "修改指定的订餐时间段")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<Timestamp> update(@ApiParam(value = "查询主键", required = true) @PathVariable()Integer id,
									  @ApiParam(value = "HH:mm:ss", required = true) @RequestParam(value = "start_time", required = true)String start_time,
									  @ApiParam(value = "HH:mm:ss", required = true) @RequestParam(value = "end_time", required = true) String end_time){
		try{
			return new JsonBean(ErrorCode.SUCCESS, timestampService.update(id,start_time,end_time));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的订餐时间段")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<Timestamp> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, timestampService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "列出所有的订餐时间段")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<Timestamp>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
	                                                    @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		return new JsonBean(ErrorCode.SUCCESS, timestampService.selectPage(offset,pageSize));
	}

	@ApiOperation(value = "列出所有的订餐时间段")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<AllResponse<Timestamp>> selectAll() {
		return new JsonBean<>(ErrorCode.SUCCESS, timestampService.selectAll());
	}

	@ApiOperation(value = "删除指定的订餐时间段")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try {
			return new JsonBean(ErrorCode.SUCCESS, timestampService.deleteByPrimaryKey(id));
		} catch (ErrorCodeException e) {
			return new JsonBean(e.getErrorCode());
		}
	}

}
