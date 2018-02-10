package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.model.Speciality;
import com.avatarcn.tourists.service.SpecialityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "/v1/speciality", description = "特产模块")
@RequestMapping(value = "/v1/speciality")
@RestController
public class SpecialityController{
	@Autowired
	private SpecialityService specialityService;

	@ApiOperation(value = "增加Speciality")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<Speciality> Insert(@ApiParam(value = "特产类型ID", required = true) @RequestParam(value = "speciality_type_id") Integer speciality_type_id,
									   @ApiParam(value = "特产名称",required = false)@RequestParam(value = "name",required = false)String name,
									   @ApiParam(value = "价格",required = false)@RequestParam(value = "price",required = false)Float price,
									   @ApiParam(value = "特产图片地址",required = false)@RequestParam(value = "url",required = false)String url,
									   @ApiParam(value = "备注",required = false)@RequestParam(value = "remark",required = false)String remark){
		try{
			return new JsonBean<>(ErrorCode.SUCCESS, specialityService.insert(speciality_type_id,name,price,url,remark));
		}catch(ErrorCodeException e){
			return new JsonBean<>(e.getErrorCode());
		}
	}

	@ApiOperation(value = "修改指定的Speciality")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<Speciality> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
									   @ApiParam(value = "特产类型ID", required = true) @RequestParam(value = "speciality_type_id") Integer speciality_type_id,
	                                   @ApiParam(value = "名称",required = false)@RequestParam(value = "name",required = false)String name,
	                                   @ApiParam(value = "价格",required = false)@RequestParam(value = "price",required = false)Float price,
	                                   @ApiParam(value = "图片地址",required = false)@RequestParam(value = "url",required = false)String url,
	                                   @ApiParam(value = "备注",required = false)@RequestParam(value = "remark",required = false)String remark){
		try{
			return new JsonBean<>(ErrorCode.SUCCESS, specialityService.update(speciality_type_id,id,name,price,url,remark));
		}catch(ErrorCodeException e){
			return new JsonBean<>(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的Speciality")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<Speciality> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean<>(ErrorCode.SUCCESS, specialityService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean<>(e.getErrorCode());
		}
	}

	@ApiOperation(value = "分页列出所有的Speciality")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<Speciality>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
														 @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		return new JsonBean<>(ErrorCode.SUCCESS, specialityService.selectPage(offset,pageSize));
	}

	@ApiOperation(value = "根据类型分页获取Speciality")
	@RequestMapping(value = "/page/{speciality_type_id}", method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<Speciality>> selectPageByTypeId(@ApiParam(value = "特产类型ID", required = true) @PathVariable(value = "speciality_type_id") Integer speciality_type_id,
																 @ApiParam(value = "从第几个开始列出") @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
																 @ApiParam(value = "每页内容数量") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
		return new JsonBean<>(ErrorCode.SUCCESS, specialityService.selectPageByType(speciality_type_id, offset, pageSize));
	}

	@ApiOperation(value = "删除指定的Speciality")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean<>(ErrorCode.SUCCESS, specialityService.deleteByPrimaryKey(id));
	}

}
