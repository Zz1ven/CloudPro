package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.model.FeedbackReply;
import com.avatarcn.tourists.service.FeedbackReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by z1ven on 2018/1/26 14:01
 */
@Api(value = "/v1/feedback/reply", description = "反馈回复表")
@RequestMapping("/v1/feedback/reply")
@RestController
public class FeedbackReplyController {

    @Autowired
    private FeedbackReplyService feedbackReplyService;

    @ApiOperation("添加一条反馈回复")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<FeedbackReply> addFeedbackReply(@ApiParam(value = "意见反馈ID", required = true) @RequestParam(value = "feedback_id") Integer feedback_id,
                                                    @ApiParam(value = "回复内容", required = true) @RequestParam(value = "content") String content) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, feedbackReplyService.addFeedbackReply(feedback_id, content));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("删除指定的反馈回复")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean<Integer> deleteFeedbackReply(@ApiParam(value = "反馈回复ID", required = true) @PathVariable(value = "id") Integer id) {
        return new JsonBean<>(ErrorCode.SUCCESS, feedbackReplyService.deleteFeedbackReply(id));
    }
}
