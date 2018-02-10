package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.global.Constant;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.mapper.*;
import com.avatarcn.tourists.model.Feedback;
import com.avatarcn.tourists.model.FeedbackImg;
import com.avatarcn.tourists.model.FeedbackType;
import com.avatarcn.tourists.model.user.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by z1ven on 2018/1/26 09:26
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 60000, rollbackFor = Exception.class)
public class FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Autowired
    private FeedbackTypeMapper feedbackTypeMapper;

    @Autowired
    private FeedbackImgMapper feedbackImgMapper;

    @Autowired
    private FeedbackReplyMapper feedbackReplyMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private OssService ossService;

    public Feedback addFeedback(Integer userId, Integer feedbackTypeId, String content, String email, String phone, List<String> images) throws ErrorCodeException {
        Account account = accountMapper.selectByServerId(String.valueOf(userId));
        if (account == null) {
            throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
        }
        FeedbackType feedbackType = feedbackTypeMapper.selectById(feedbackTypeId);
        if (feedbackType == null) {
            throw new ErrorCodeException(TouristsErrorCode.FEEDBACK_TYPE_NULL);
        }
        Feedback feedback = new Feedback();
        feedback.setFk_tb_user_id(account.getId());
        feedback.setFk_tb_feedback_type_id(feedbackTypeId);
        feedback.setContent(content);
        feedback.setEmail(email);
        feedback.setPhone(phone);
        feedback.setTime(new Date());
        feedbackMapper.insert(feedback);
        List<FeedbackImg> feedbackImgList = new ArrayList<>();
        if (images != null && !images.isEmpty()) {
            for (String url : images) {
                String newUrl = ossService.copyFileTo(url, Constant.TOURISTS_TMP_DIR, Constant.TOURISTS_FEEDBACK_DIR);
                FeedbackImg feedbackImg = new FeedbackImg();
                feedbackImg.setFk_tb_feedback_id(feedback.getId());
                feedbackImg.setImage(newUrl);
                feedbackImgMapper.insert(feedbackImg);
                feedbackImgList.add(feedbackImg);
            }
        }
        feedback.setFeedbackType(feedbackType);
        feedback.setFeedbackImgList(feedbackImgList);
        return feedback;
    }

    public int deleteFeedback(Integer id) throws ErrorCodeException {
        Feedback feedback = feedbackMapper.selectById(id);
        if (feedback == null) {
            throw new ErrorCodeException(TouristsErrorCode.FEEDBACK_NULL);
        }
        //删除关联图片和关联回复
        List<FeedbackImg> feedbackImgList = feedback.getFeedbackImgList();
        for (FeedbackImg feedbackImg : feedbackImgList) {
            ossService.delete(feedbackImg.getImage());
        }
        feedbackImgMapper.deleteByFeedbackId(id);
        feedbackReplyMapper.deleteByFeedbackId(id);
        if (feedbackMapper.deleteById(id) != 1) {
            throw new ErrorCodeException(ErrorCodeException.DELETE_NO);
        }
        return 1;
    }

    public Feedback selectById(Integer id) {
        return feedbackMapper.selectById(id);
    }

    public PageResponse<Feedback> selectPageByUserId(Integer userId, Integer offset, Integer pageSize) throws ErrorCodeException {
        Account account = accountMapper.selectByServerId(String.valueOf(userId));
        if (account == null) {
            throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
        }
        PageResponse<Feedback> feedbackPageResponse = new PageResponse<>();
        feedbackPageResponse.setItem(feedbackMapper.selectPageByUserId(account.getId(), offset, pageSize));
        feedbackPageResponse.setOffset(offset);
        feedbackPageResponse.setPageSize(pageSize);
        feedbackPageResponse.setTotal(feedbackMapper.countByUserId(account.getId()));
        return feedbackPageResponse;
    }

    public PageResponse<Feedback> selectPage(Integer offset, Integer pageSize) {
        PageResponse<Feedback> feedbackPageResponse = new PageResponse<>();
        feedbackPageResponse.setItem(feedbackMapper.selectPage(offset, pageSize));
        feedbackPageResponse.setOffset(offset);
        feedbackPageResponse.setPageSize(pageSize);
        feedbackPageResponse.setTotal(feedbackMapper.count());
        return feedbackPageResponse;
    }
}
