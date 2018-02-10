package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.feign.UserServiceFeign;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.news.NewsUpResponse;
import com.avatarcn.tourists.mapper.AccountMapper;
import com.avatarcn.tourists.mapper.NewsMapper;
import com.avatarcn.tourists.mapper.NewsUpMapper;
import com.avatarcn.tourists.model.News;
import com.avatarcn.tourists.model.NewsUp;
import com.avatarcn.tourists.model.user.Account;
import com.avatarcn.tourists.model.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by z1ven on 2018/1/17 17:07
 */
@Service
public class NewsUpService {

    @Autowired
    private NewsUpMapper newsUpMapper;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private UserServiceFeign userServiceFeign;

    @Autowired
    private AccountMapper accountMapper;

    public NewsUpResponse addNewsUp(Integer userId, Integer newsId) throws ErrorCodeException {
        JsonBean<UserInfo> userInfoJsonBean = userServiceFeign.info_get(userId);
        if (!userInfoJsonBean.isSuccess()) {
            throw new ErrorCodeException(new ErrorCode(userInfoJsonBean.getError_code(), userInfoJsonBean.getMsg()));
        }
        News news = newsMapper.selectById(newsId);
        if (news == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        Account account = accountMapper.selectByServerId(String.valueOf(userId));
        if (account == null) {
            throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
        }
        UserInfo userInfo = userInfoJsonBean.getData();
        NewsUp newsUp = newsUpMapper.selectByNewsIdAndUserId(newsId, userId);
        NewsUpResponse newsUpResponse = new NewsUpResponse();
        if (newsUp == null) {
            newsUp = new NewsUp();
            newsUp.setFk_tb_user_id(account.getId());
            newsUp.setFk_tb_news_id(newsId);
            newsUp.setTime(new Date());
            newsUpMapper.insert(newsUp);
        }
        newsUpResponse.setId(newsUp.getId());
        newsUpResponse.setNews_id(newsUp.getFk_tb_news_id());
        newsUpResponse.setUser_id(newsUp.getFk_tb_user_id());
        newsUpResponse.setNickname(userInfo.getNickname());
        newsUpResponse.setUserUrl(userInfo.getImg());
        return newsUpResponse;
    }

    public int deleteNewsUp(Integer userId, Integer newsId) throws ErrorCodeException {
        Account account = accountMapper.selectByServerId(String.valueOf(userId));
        if (account == null) {
            throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
        }
        if (newsUpMapper.deleteByNewsIdAndUserId(newsId, account.getId()) != 1) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        return 1;
    }

    /**
     * 用户是否点赞该新闻
     * @return
     */
    public boolean userIsUp(Integer userId, Integer newsId) throws ErrorCodeException {
        Account account = accountMapper.selectByServerId(String.valueOf(userId));
        if (account == null) {
            throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
        }
        NewsUp newsUp = newsUpMapper.selectByNewsIdAndUserId(newsId, account.getId());
        if (newsUp != null) {
            return true;
        }
        return false;
    }
}
