package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.feign.UserServiceFeign;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.response.news.NewsCommentAndUpResponse;
import com.avatarcn.tourists.json.response.news.NewsCommentResponse;
import com.avatarcn.tourists.mapper.AccountMapper;
import com.avatarcn.tourists.mapper.NewsCommentMapper;
import com.avatarcn.tourists.mapper.NewsMapper;
import com.avatarcn.tourists.mapper.NewsUpMapper;
import com.avatarcn.tourists.model.News;
import com.avatarcn.tourists.model.NewsComment;
import com.avatarcn.tourists.model.user.Account;
import com.avatarcn.tourists.model.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by z1ven on 2018/1/17 16:27
 */
@Service
public class NewsCommentService {

    @Autowired
    private NewsCommentMapper newsCommentMapper;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private NewsUpMapper newsUpMapper;

    @Autowired
    private UserServiceFeign userServiceFeign;

    @Autowired
    private AccountMapper accountMapper;

    public NewsCommentResponse addNewsComment(Integer userId, Integer newsId, String content) throws ErrorCodeException {
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
        NewsComment newsComment = new NewsComment();
        newsComment.setFk_tb_user_id(account.getId());
        newsComment.setFk_tb_news_id(newsId);
        newsComment.setContent(content);
        newsComment.setTime(new Date());
        newsCommentMapper.insert(newsComment);
        NewsCommentResponse newsCommentResponse = new NewsCommentResponse();
        newsCommentResponse.setId(newsComment.getId());
        newsCommentResponse.setContent(newsComment.getContent());
        newsCommentResponse.setUser_id(newsComment.getFk_tb_user_id());
        newsCommentResponse.setNickname(userInfo.getNickname());
        newsCommentResponse.setUserUrl(userInfo.getImg());
        newsCommentResponse.setNews_id(newsComment.getFk_tb_news_id());
        newsCommentResponse.setTime(newsComment.getTime());
        return newsCommentResponse;
    }

    public int deleteCommentById(Integer id) throws ErrorCodeException {
        if (newsCommentMapper.deleteById(id) != 1) {
            throw new ErrorCodeException(ErrorCodeException.DELETE_NO);
        }
        return 1;
    }

    /**
     * 分页获取指定新闻的评论列表和点赞数
     * @return
     */
    public NewsCommentAndUpResponse getPageNewsCommentAndUp(Integer newsId, Integer offset, Integer pageSize) throws ErrorCodeException {
        NewsCommentAndUpResponse newsCommentAndUpResponse = new NewsCommentAndUpResponse();
        List<NewsCommentResponse> commentResponseList = new ArrayList<>();
        List<NewsComment> newsCommentList = newsCommentMapper.selectPageByNewsId(newsId, offset, pageSize);
        for (NewsComment newsComment : newsCommentList) {
            Account account = accountMapper.selectById(newsComment.getFk_tb_user_id());
            if (account == null) {
                throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
            }
            JsonBean<UserInfo> userInfoJsonBean = userServiceFeign.info_get(Integer.valueOf(account.getServer_id()));
            if (!userInfoJsonBean.isSuccess()) {
                //throw new ErrorCodeException(new ErrorCode(userInfoJsonBean.getError_code(), userInfoJsonBean.getMsg()));
                continue;
            }
            UserInfo userInfo = userInfoJsonBean.getData();
            NewsCommentResponse newsCommentResponse = new NewsCommentResponse();
            newsCommentResponse.setId(newsComment.getId());
            newsCommentResponse.setContent(newsComment.getContent());
            newsCommentResponse.setUser_id(newsComment.getFk_tb_user_id());
            newsCommentResponse.setNickname(userInfo.getNickname());
            newsCommentResponse.setUserUrl(userInfo.getImg());
            newsCommentResponse.setNews_id(newsComment.getFk_tb_news_id());
            newsCommentResponse.setTime(newsComment.getTime());
            commentResponseList.add(newsCommentResponse);
        }
        newsCommentAndUpResponse.setComments(commentResponseList);
        newsCommentAndUpResponse.setOffset(offset);
        newsCommentAndUpResponse.setPageSize(pageSize);
        newsCommentAndUpResponse.setCommentCount(newsCommentMapper.countWithNewsId(newsId));
        newsCommentAndUpResponse.setUpCount(newsUpMapper.countWithNewsId(newsId));
        return newsCommentAndUpResponse;
    }

}
