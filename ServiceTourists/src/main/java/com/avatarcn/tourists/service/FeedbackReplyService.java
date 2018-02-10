package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.mapper.FeedbackMapper;
import com.avatarcn.tourists.mapper.FeedbackReplyMapper;
import com.avatarcn.tourists.model.FeedbackReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by z1ven on 2018/1/26 13:51
 */
@Service
public class FeedbackReplyService {

    @Autowired
    private FeedbackReplyMapper feedbackReplyMapper;

    @Autowired
    private FeedbackMapper feedbackMapper;

    public FeedbackReply addFeedbackReply(Integer feedback_id, String content) throws ErrorCodeException {
        if (feedbackMapper.selectById(feedback_id) == null) {
            throw new ErrorCodeException(TouristsErrorCode.FEEDBACK_NULL);
        }
        FeedbackReply feedbackReply = new FeedbackReply();
        feedbackReply.setFk_tb_feedback_id(feedback_id);
        feedbackReply.setContent(content);
        feedbackReply.setTime(new Date());
        feedbackReplyMapper.insert(feedbackReply);
        return feedbackReply;
    }

    public int deleteFeedbackReply(Integer id) {
        return feedbackReplyMapper.deleteById(id);
    }
}
