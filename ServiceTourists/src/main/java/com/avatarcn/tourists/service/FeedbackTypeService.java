package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.json.response.AllResponse;
import com.avatarcn.tourists.mapper.FeedbackMapper;
import com.avatarcn.tourists.mapper.FeedbackTypeMapper;
import com.avatarcn.tourists.model.Feedback;
import com.avatarcn.tourists.model.FeedbackType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by z1ven on 2018/1/26 09:26
 */
@Service
public class FeedbackTypeService {

    @Autowired
    private FeedbackTypeMapper feedbackTypeMapper;

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Autowired
    private FeedbackService feedbackService;

    public FeedbackType addFeedbackType(String name) throws ErrorCodeException {
        FeedbackType feedbackType = feedbackTypeMapper.selectByName(name);
        if (feedbackType != null) {
            throw new ErrorCodeException(TouristsErrorCode.FEEDBACK_TYPE_REPEAT);
        }
        feedbackType = new FeedbackType();
        feedbackType.setName(name);
        feedbackTypeMapper.insert(feedbackType);
        return feedbackType;
    }

    public int deleteFeedbackType(Integer id) throws ErrorCodeException {
        FeedbackType feedbackType = feedbackTypeMapper.selectById(id);
        if (feedbackType == null) {
            throw new ErrorCodeException(TouristsErrorCode.FEEDBACK_TYPE_NULL);
        }
        //删除关联的反馈
        List<Feedback> feedbackList = feedbackMapper.selectByFeedbackTypeId(id);
        for (Feedback feedback : feedbackList) {
            feedbackService.deleteFeedback(feedback.getId());
        }
        if (feedbackTypeMapper.deleteById(id) != 1) {
            throw new ErrorCodeException(ErrorCodeException.DELETE_NO);
        }
        return 1;
    }

    public FeedbackType selectById(Integer id) {
        return feedbackTypeMapper.selectById(id);
    }

    public AllResponse<FeedbackType> selectAll() {
        List<FeedbackType> feedbackTypes = feedbackTypeMapper.selectAll();
        AllResponse<FeedbackType> feedbackTypeAllResponse = new AllResponse<>();
        feedbackTypeAllResponse.setItem(feedbackTypes);
        feedbackTypeAllResponse.setTotal(feedbackTypes.size());
        return feedbackTypeAllResponse;
    }

    public FeedbackType update(Integer id, String name) throws ErrorCodeException {
        FeedbackType feedbackType = feedbackTypeMapper.selectById(id);
        if (feedbackType == null) {
            throw new ErrorCodeException(TouristsErrorCode.FEEDBACK_TYPE_NULL);
        }
        if (feedbackTypeMapper.selectByName(name) != null) {
            throw new ErrorCodeException(TouristsErrorCode.FEEDBACK_TYPE_REPEAT);
        }
        feedbackType.setName(name);
        feedbackTypeMapper.update(feedbackType);
        return feedbackType;
    }
}
