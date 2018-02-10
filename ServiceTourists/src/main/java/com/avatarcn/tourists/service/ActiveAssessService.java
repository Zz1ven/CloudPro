package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.feign.UserServiceFeign;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.active.ActiveAssessResponse;
import com.avatarcn.tourists.json.response.active.PageActiveAssessResponse;
import com.avatarcn.tourists.mapper.AccountMapper;
import com.avatarcn.tourists.mapper.ActiveAssessMapper;
import com.avatarcn.tourists.mapper.ActiveOrderMapper;
import com.avatarcn.tourists.model.ActiveAssess;
import com.avatarcn.tourists.model.ActiveOrder;
import com.avatarcn.tourists.model.user.Account;
import com.avatarcn.tourists.model.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by z1ven on 2018/1/19 13:16
 */
@Service
public class ActiveAssessService {

    @Autowired
    private ActiveAssessMapper activeAssessMapper;

    @Autowired
    private UserServiceFeign userServiceFeign;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private ActiveOrderMapper activeOrderMapper;

    public ActiveAssessResponse addActiveAssess(Integer userId, Integer orderId, float score, String assess) throws ErrorCodeException {
        JsonBean<UserInfo> userInfoJsonBean = userServiceFeign.info_get(userId);
        if (!userInfoJsonBean.isSuccess()) {
            throw new ErrorCodeException(new ErrorCode(userInfoJsonBean.getError_code(), userInfoJsonBean.getMsg()));
        }
        ActiveOrder activeOrder = activeOrderMapper.selectById(orderId);
        if (activeOrder == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        Account account = accountMapper.selectByServerId(String.valueOf(userId));
        if (account == null) {
            throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
        }
        if (account.getId() != activeOrder.getFk_tb_user_id()) {
            throw new ErrorCodeException(TouristsErrorCode.ORDER_NULL);
        }
        int count = activeAssessMapper.countByUserIdAndActiveId(account.getId(), activeOrder.getFk_tb_active_id());
        if (count > 0) {//已经评论过
            throw new ErrorCodeException(TouristsErrorCode.ASSESS_REPEAT);
        }
        ActiveAssess activeAssess = new ActiveAssess();
        activeAssess.setFk_tb_user_id(account.getId());
        activeAssess.setFk_tb_active_id(activeOrder.getFk_tb_active_id());
        activeAssess.setScore(score);
        activeAssess.setAssess(assess);
        activeAssess.setTime(new Date());
        activeAssessMapper.insert(activeAssess);
        ActiveAssessResponse activeAssessResponse = new ActiveAssessResponse();
        activeAssessResponse.setId(activeAssess.getId());
        activeAssessResponse.setUser_id(activeAssess.getFk_tb_user_id());
        activeAssessResponse.setNickname(userInfoJsonBean.getData().getNickname());
        activeAssessResponse.setUserUrl(userInfoJsonBean.getData().getImg());
        activeAssessResponse.setActive_id(activeAssess.getFk_tb_active_id());
        activeAssessResponse.setScore(activeAssess.getScore());
        activeAssessResponse.setAssess(activeAssess.getAssess());
        activeAssessResponse.setTime(activeAssess.getTime());
        return activeAssessResponse;
    }

    public int deleteActiveAssess(Integer id) throws ErrorCodeException {
        if (activeAssessMapper.deleteById(id) != 1) {
            throw new ErrorCodeException(ErrorCodeException.DELETE_NO);
        }
        return 1;
    }

    public PageActiveAssessResponse getPageAssess(Integer activeId, Integer offset, Integer pageSize) throws ErrorCodeException {
        PageActiveAssessResponse pageActiveAssessResponse = new PageActiveAssessResponse();
        List<ActiveAssessResponse> activeAssessResponseList = new ArrayList<>();
        List<ActiveAssess> activeAssessList = activeAssessMapper.selectPageByActiveId(activeId, offset, pageSize);
        for (ActiveAssess activeAssess : activeAssessList) {
            Account account = accountMapper.selectById(activeAssess.getFk_tb_user_id());
            if (account == null) {
                throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
            }
            JsonBean<UserInfo> userInfoJsonBean = userServiceFeign.info_get(Integer.valueOf(account.getServer_id()));
            if (!userInfoJsonBean.isSuccess()) {
                continue;
            }
            ActiveAssessResponse activeAssessResponse = new ActiveAssessResponse();
            activeAssessResponse.setId(activeAssess.getId());
            activeAssessResponse.setUser_id(activeAssess.getFk_tb_user_id());
            activeAssessResponse.setActive_id(activeAssess.getFk_tb_active_id());
            activeAssessResponse.setScore(activeAssess.getScore());
            activeAssessResponse.setAssess(activeAssess.getAssess());
            activeAssessResponse.setTime(activeAssess.getTime());
            activeAssessResponse.setNickname(userInfoJsonBean.getData().getNickname());
            activeAssessResponse.setUserUrl(userInfoJsonBean.getData().getImg());
            activeAssessResponseList.add(activeAssessResponse);
        }
        int total = activeAssessMapper.countByActiveId(activeId);
        float score = activeAssessMapper.sumScoreWithActive(activeId) / total;
        pageActiveAssessResponse.setAssesses(activeAssessResponseList);
        pageActiveAssessResponse.setOffset(offset);
        pageActiveAssessResponse.setPageSize(pageSize);
        pageActiveAssessResponse.setAssessCount(total);
        pageActiveAssessResponse.setAverageScore(score);
        return pageActiveAssessResponse;
    }
}
