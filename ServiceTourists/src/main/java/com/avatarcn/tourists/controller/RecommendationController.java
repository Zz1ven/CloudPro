package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.model.Recommendation;
import com.avatarcn.tourists.service.RecommendationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by z1ven on 2018/1/31 16:28
 */
@Api(value = "/v1/recommendation", description = "推荐商品模块")
@RequestMapping(value = "/v1/recommendation")
@RestController
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @ApiOperation("添加一个推荐商品")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<Recommendation> addRecommendation(@ApiParam(value = "商品图片", required = true) @RequestParam(value = "image") String image) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, recommendationService.addRecommendation(image));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("删除指定的推荐商品")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean<Integer> deleteById(@ApiParam(value = "主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, recommendationService.deleteById(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("获取指定的推荐商品")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Recommendation> selectById(@ApiParam(value = "主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, recommendationService.selectById(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("获取所有的推荐商品")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<AllResponse<Recommendation>> selectAll() {
        return new JsonBean<>(ErrorCode.SUCCESS, recommendationService.selectAll());
    }

    @ApiOperation("修改指定的推荐商品")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<Recommendation> update(@ApiParam(value = "主键ID", required = true) @PathVariable(value = "id") Integer id,
                                           @ApiParam(value = "商品图片", required = true) @RequestParam(value = "image") String image) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, recommendationService.update(id, image));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }
}
