package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.json.response.RoomResponse;
import com.avatarcn.tourists.model.Room;
import com.avatarcn.tourists.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Api(value = "/v1/room", description = "房间模块")
@RequestMapping(value = "/v1/room")
@RestController
public class RoomController{
	@Autowired
	private RoomService roomService;

	@ApiOperation(value = "增加Room")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<Room> Insert(@ApiParam(value = "房间图片",required = false)@RequestParam(value = "img",required = true)String img,
								 @ApiParam(value = "房间名称",required = false)@RequestParam(value = "name",required = true)String name,
								 @ApiParam(value = "房间号",required = false)@RequestParam(value = "number",required = true)String number,
								 @ApiParam(value = "房间容纳人数",required = false)@RequestParam(value = "people",required = true)Integer people){
		try{
			return new JsonBean(ErrorCode.SUCCESS, roomService.insert(img,name,number,people));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "修改指定的Room")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<Room> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
	                             @ApiParam(value = "房间图片",required = false)@RequestParam(value = "img",required = true)String img,
	                             @ApiParam(value = "房间名称",required = false)@RequestParam(value = "name",required = true)String name,
	                             @ApiParam(value = "房间号",required = false)@RequestParam(value = "number",required = true)String number,
	                             @ApiParam(value = "房间容纳人数",required = false)@RequestParam(value = "people",required = true)Integer people){
		try{
			return new JsonBean(ErrorCode.SUCCESS, roomService.update(id,img,name,number,people));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的Room")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<Room> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, roomService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}



	@ApiOperation(value = "列出所有的Room")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<Room>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
												   @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		return new JsonBean(ErrorCode.SUCCESS, roomService.selectPage(offset,pageSize));
	}

	@ApiOperation(value = "根据时间列出所有的房间(用)")
	@RequestMapping(value = "time/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<RoomResponse> selectByTimePage(@ApiParam(value = "具体天（yyyy-MM-dd)", required = true) @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date time,
												   @ApiParam(value = "时间点id",required = true)@RequestParam(value = "fk_tb_time_id",required = true)Integer fk_tb_time_id) {
		try {
			return new JsonBean(ErrorCode.SUCCESS, roomService.selectByTimePage(time, fk_tb_time_id));
		} catch (ErrorCodeException e) {
			return new JsonBean(e.getErrorCode());
		}

	}



	@ApiOperation(value = "删除指定的Room")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, roomService.deleteByPrimaryKey(id));
	}

}
