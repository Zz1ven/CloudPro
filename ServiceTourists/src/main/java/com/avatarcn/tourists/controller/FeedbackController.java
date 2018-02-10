package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.feign.UserServiceFeign;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.model.Feedback;
import com.avatarcn.tourists.model.user.User;
import com.avatarcn.tourists.service.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by z1ven on 2018/1/26 10:35
 */
@Api(value = "/v1/feedback", description = "意见反馈模块")
@RequestMapping(value = "/v1/feedback")
@RestController
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserServiceFeign userServiceFeign;

    @ApiOperation("添加一个意见反馈")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<Feedback> addFeedback(@ApiParam(value = "token", required = true) @RequestParam(value = "token") String token,
                                          @ApiParam(value = "反馈类型ID", required = true) @RequestParam(value = "feedback_type_id") Integer feedback_type_id,
                                          @ApiParam(value = "反馈内容", required = true) @RequestParam(value = "content") String content,
                                          @ApiParam(value = "邮箱") @RequestParam(value = "email", required = false) String email,
                                          @ApiParam(value = "手机号") @RequestParam(value = "phone", required = false) String phone,
                                          @ApiParam(value = "反馈图片") @RequestParam(value = "image_urls", required = false) List<String> image_urls) {
        JsonBean<User> userJsonBean =userServiceFeign.getUser(token);
        if (!userJsonBean.isSuccess()) {
            return new JsonBean<>(new ErrorCode(userJsonBean.getError_code(), userJsonBean.getMsg()));
        }
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, feedbackService.addFeedback(userJsonBean.getData().getId(), feedback_type_id, content, email, phone, image_urls));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("删除指定的反馈")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean<Integer> deleteFeedback(@ApiParam(value = "反馈ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, feedbackService.deleteFeedback(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("获取指定的反馈")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Feedback> getFeedback(@ApiParam(value = "反馈ID", required = true) @PathVariable(value = "id") Integer id) {
        return new JsonBean<>(ErrorCode.SUCCESS, feedbackService.selectById(id));
    }

    @ApiOperation("分页获取指定用户的反馈")
    @RequestMapping(value = "/page/user", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<Feedback>> getPageByUser(@ApiParam(value = "token", required = true) @RequestParam(value = "token") String token,
                                                          @ApiParam(value = "从第几个开始") @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                                          @ApiParam(value = "每页的个数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        JsonBean<User> userJsonBean = userServiceFeign.getUser(token);
        if (!userJsonBean.isSuccess()) {
            return new JsonBean<>(new ErrorCode(userJsonBean.getError_code(), userJsonBean.getMsg()));
        }
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, feedbackService.selectPageByUserId(userJsonBean.getData().getId(), offset, pageSize));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("分页获取所有的反馈")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<Feedback>> getPage(@ApiParam(value = "从第几个开始") @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                                    @ApiParam(value = "每页的个数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return new JsonBean<>(ErrorCode.SUCCESS, feedbackService.selectPage(offset, pageSize));
    }
}
