package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.json.response.active.ActiveResponse;
import com.avatarcn.tourists.model.Active;
import com.avatarcn.tourists.model.ActiveImg;
import com.avatarcn.tourists.model.ActiveRemark;
import com.avatarcn.tourists.service.ActiveImgService;
import com.avatarcn.tourists.service.ActiveRemarkService;
import com.avatarcn.tourists.service.ActiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by z1ven on 2018/1/18 15:17
 */
@Api(value = "/v1/active", description = "活动模块")
@RequestMapping(value = "/v1/active")
@RestController
public class ActiveController {

    @Autowired
    private ActiveService activeService;

    @Autowired
    private ActiveRemarkService activeRemarkService;

    @Autowired
    private ActiveImgService activeImgService;

    @ApiOperation("添加活动")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<Active> addActive(@ApiParam(value = "活动名", required = true) @RequestParam String name,
                                      @ApiParam(value = "活动图片", required = true) @RequestParam String icon,
                                      @ApiParam(value = "活动开始时间", required = true) @RequestParam long start_time,
                                      @ApiParam(value = "活动结束时间", required = true) @RequestParam long end_time,
                                      @ApiParam(value = "价格", required = true) @RequestParam Float price,
                                      @ApiParam(value = "活动详情") @RequestParam(value = "description", required = false) String description,
                                      @ApiParam(value = "活动关联图片") @RequestParam(value = "images_url", required = false) List<String> images_url,
                                      @ApiParam(value = "活动关联标签") @RequestParam(value = "remarks_id", required = false) List<Integer> remarks_id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, activeService.addActive(name, icon, start_time, end_time, price, description, images_url, remarks_id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("删除指定的活动")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean<Integer> deleteActive(@ApiParam(value = "活动ID", required = true) @PathVariable Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, activeService.deleteActive(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("分页获取活动")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<ActiveResponse>> getPageActive(@ApiParam(value = "从第几个开始") @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                                                @ApiParam(value = "每页的个数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return new JsonBean<>(ErrorCode.SUCCESS, activeService.selectPageActive(offset, pageSize));
    }

    @ApiOperation("获取指定的活动")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Active> getActiveById(@ApiParam(value = "活动ID", required = true) @PathVariable Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, activeService.selectById(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("修改指定的活动")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<Active> editActive(@ApiParam(value = "活动ID", required = true) @PathVariable Integer id,
                                       @ApiParam(value = "活动名", required = true) @RequestParam String name,
                                       @ApiParam(value = "活动图片", required = true) @RequestParam String icon,
                                       @ApiParam(value = "活动开始时间", required = true) @RequestParam Long start_time,
                                       @ApiParam(value = "活动结束时间", required = true) @RequestParam Long end_time,
                                       @ApiParam(value = "价格", required = true) @RequestParam Float price,
                                       @ApiParam(value = "活动详情") @RequestParam(value = "description", required = false) String description,
                                       @ApiParam(value = "活动关联图片") @RequestParam(value = "images_url", required = false) List<String> images_url,
                                       @ApiParam(value = "活动关联标签") @RequestParam(value = "remarks_id", required = false) List<Integer> remarks_id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, activeService.updateActive(id, name, icon, start_time, end_time, price, description, images_url, remarks_id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("活动添加一个标签")
    @RequestMapping(value = "/remark", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<ActiveRemark> addActiveRemark(@ApiParam(value = "活动ID", required = true) @RequestParam Integer active_id,
                                                  @ApiParam(value = "标签ID", required = true) @RequestParam Integer remark_id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, activeRemarkService.addActiveRemark(active_id, remark_id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("活动删除一个标签")
    @RequestMapping(value = "/remark/{remark_id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean<Integer> deleteActiveRemark(@ApiParam(value = "活动ID", required = true) @RequestParam Integer active_id,
                                                @ApiParam(value = "标签ID", required = true) @PathVariable Integer remark_id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, activeRemarkService.deleteActiveRemark(active_id, remark_id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("活动添加一个图片")
    @RequestMapping(value = "/img", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<ActiveImg> addActiveImg(@ApiParam(value = "活动ID", required = true) @RequestParam Integer active_id,
                                            @ApiParam(value = "图片url", required = true) @RequestParam String img_url) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, activeImgService.addActiveImg(active_id, img_url));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("活动删除一个图片")
    @RequestMapping(value = "/img", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean<Integer> deleteActiveImg(@ApiParam(value = "活动ID", required = true) @RequestParam Integer active_id,
                                             @ApiParam(value = "图片url", required = true) @RequestParam String img_url) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, activeImgService.deleteActiveImg(active_id, img_url));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }
}
