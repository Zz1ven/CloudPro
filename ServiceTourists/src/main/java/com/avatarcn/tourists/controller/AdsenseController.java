package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.body.AdsenseBody;
import com.avatarcn.tourists.json.response.AdsenseResponse;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.model.Adsense;
import com.avatarcn.tourists.service.AdsenseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Api(value = "/v1/adsense", description = "广告模块")
@RequestMapping(value = "/v1/adsense")
@RestController
public class AdsenseController{
	@Autowired
	private AdsenseService adsenseService;

	@ApiOperation(value = "增加Adsense")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<Adsense> Insert(@RequestBody AdsenseBody adsenseBody){
		try{
			return new JsonBean(ErrorCode.SUCCESS, adsenseService.insert(adsenseBody));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "修改指定的Adsense")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<Adsense> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
									@RequestBody AdsenseBody adsenseBody){
		try{
			return new JsonBean(ErrorCode.SUCCESS, adsenseService.update(id,adsenseBody));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}


	@ApiOperation(value = "获取指定的Adsense")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<AdsenseResponse> selectByPrimaryKey(@PathVariable Integer id,
														HttpServletRequest request){
		try{
			return new JsonBean(ErrorCode.SUCCESS, adsenseService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "列出所有的Adsense")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<AdsenseResponse>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
													  @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		return new JsonBean<>(ErrorCode.SUCCESS, adsenseService.GetPageAdsense(offset,pageSize));
	}

	@ApiOperation(value = "删除指定的Adsense")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, adsenseService.deleteByPrimaryKey(id));
	}


	@ApiOperation(value = "获取指定修改的广告")
	@RequestMapping(value = "update/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<Adsense> GetAdsense(@PathVariable Integer id) {
		try{
			return new JsonBean(ErrorCode.SUCCESS,adsenseService.GetAdsense(id));
		} catch (ErrorCodeException e) {
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "阅读指定的广告")
	@RequestMapping(value = "/{id}/read",method = RequestMethod.GET)
	@ResponseBody
	public String   read(@PathVariable Integer id) {
		try{
			Adsense adsense=adsenseService.GetAdsense(id);
			return  adsense.getDetailurl();
		} catch (Exception e) {
			return e.getMessage();
		}
	}


}
