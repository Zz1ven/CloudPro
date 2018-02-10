package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.model.Promotion;
import com.avatarcn.tourists.service.PromotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by z1ven on 2018/2/7 17:53
 */
@Api(value = "/v1/promotion", description = "优惠活动模块")
@RequestMapping(value = "/v1/promotion")
@RestController
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @ApiOperation("添加一个优惠活动")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<Promotion> addPromotion(@ApiParam(value = "活动名", required = true) @RequestParam(value = "name") String name,
                                            @ApiParam(value = "活动描述") @RequestParam(value = "description", required = false) String description,
                                            @ApiParam(value = "活动地址链接") @RequestParam(value = "url", required = false) String url) {
        return new JsonBean<>(ErrorCode.SUCCESS, promotionService.insert(name, description, url));
    }

    @ApiOperation("删除指定的优惠活动")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean<Integer> deleteById(@ApiParam(value = "主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, promotionService.deleteById(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("获取指定的优惠活动")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Promotion> getPromotion(@ApiParam(value = "主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, promotionService.selectById(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("分页获取所有的优惠活动")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<Promotion>> getPage(@ApiParam(value = "从第几个开始") @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                                     @ApiParam(value = "每页的个数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return new JsonBean<>(ErrorCode.SUCCESS, promotionService.selectPage(offset, pageSize));
    }

    @ApiOperation("修改指定的优惠活动")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<Promotion> updatePromotion(@ApiParam(value = "主键ID", required = true) @PathVariable(value = "id") Integer id,
                                               @ApiParam(value = "活动名", required = true) @RequestParam(value = "name") String name,
                                               @ApiParam(value = "活动描述") @RequestParam(value = "description", required = false) String description,
                                               @ApiParam(value = "活动地址链接") @RequestParam(value = "url", required = false) String url) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, promotionService.update(id, name, description, url));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }
}
