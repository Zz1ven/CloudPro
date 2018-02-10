package com.avatarcn.tourists.controller;

import com.avatarcn.tourists.exception.ErrorCode;
import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.feign.UserServiceFeign;
import com.avatarcn.tourists.json.JsonBean;
import com.avatarcn.tourists.json.body.PostNews;
import com.avatarcn.tourists.json.response.*;
import com.avatarcn.tourists.json.response.news.NewsCommentAndUpResponse;
import com.avatarcn.tourists.json.response.news.NewsCommentResponse;
import com.avatarcn.tourists.json.response.news.NewsResponse;
import com.avatarcn.tourists.json.response.news.NewsUpResponse;
import com.avatarcn.tourists.model.News;
import com.avatarcn.tourists.model.user.User;
import com.avatarcn.tourists.service.NewsCommentService;
import com.avatarcn.tourists.service.NewsService;
import com.avatarcn.tourists.service.NewsUpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by z1ven on 2018/1/17 17:40
 */
@Api(value = "/v1/news", description = "新闻模块")
@RequestMapping(value = "/v1/news")
@RestController
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsCommentService newsCommentService;

    @Autowired
    private NewsUpService newsUpService;

    @Autowired
    private UserServiceFeign userServiceFeign;

    @ApiOperation("添加一条新闻")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<News> addNews(@RequestBody PostNews postNews) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, newsService.addNews(postNews));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("删除指定新闻")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean<Integer> deleteNews(@ApiParam(value = "新闻主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, newsService.deleteNews(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("获取指定的新闻")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<News> getNews(@ApiParam(value = "新闻主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, newsService.selectOneNews(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("获取指定的新闻及评论点赞")
    @RequestMapping(value = "/response/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<NewsResponse> getNewsResponse(@ApiParam(value = "新闻主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, newsService.selectNewsResponse(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("阅读指定的新闻")
    @RequestMapping(value = "/{id}/read", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<News> readNews(@ApiParam(value = "新闻主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, newsService.readNews(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("分页获取新闻")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<NewsResponse>> getPageNews(@ApiParam(value = "从第几个开始") @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                                            @ApiParam(value = "每页的个数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return new JsonBean<>(ErrorCode.SUCCESS, newsService.selectPageNews(offset, pageSize));
    }

    @ApiOperation("修改指定的新闻")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<News> editNews(@ApiParam(value = "新闻主键ID", required = true) @PathVariable(value = "id") Integer id,
                             @RequestBody PostNews postNews) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, newsService.editNews(id, postNews));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("添加一条新闻评论")
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<NewsCommentResponse> addNewsComment(@ApiParam(value = "token", required = true) @RequestParam String token,
                                                        @ApiParam(value = "新闻ID", required = true) @RequestParam Integer news_id,
                                                        @ApiParam(value = "评论内容", required = true) @RequestParam String content) {
        JsonBean<User> userJsonBean = userServiceFeign.getUser(token);
        if (!userJsonBean.isSuccess()) {
            return new JsonBean<>(new ErrorCode(userJsonBean.getError_code(), userJsonBean.getMsg()));
        }
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, newsCommentService.addNewsComment(userJsonBean.getData().getId(), news_id, content));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("删除指定的评论")
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean<Integer> deleteNewsComment(@ApiParam(value = "评论主键ID", required = true) @PathVariable(value = "id") Integer id) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, newsCommentService.deleteCommentById(id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("分页获取指定新闻的评论和点赞数")
    @RequestMapping(value = "/comment/page", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<NewsCommentAndUpResponse> getPageNewsCommentAndUp(@ApiParam(value = "新闻ID", required = true) @RequestParam Integer news_id,
                                                                      @ApiParam(value = "从第几个开始") @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                                                      @ApiParam(value = "每页的个数") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, newsCommentService.getPageNewsCommentAndUp(news_id, offset, pageSize));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("点赞新闻")
    @RequestMapping(value = "/up", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<NewsUpResponse> addNewsUp(@ApiParam(value = "token", required = true) @RequestParam String token,
                                              @ApiParam(value = "新闻ID", required = true) @RequestParam Integer news_id) {
        JsonBean<User> userJsonBean = userServiceFeign.getUser(token);
        if (!userJsonBean.isSuccess()) {
            return new JsonBean<>(new ErrorCode(userJsonBean.getError_code(), userJsonBean.getMsg()));
        }
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, newsUpService.addNewsUp(userJsonBean.getData().getId(), news_id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("取消新闻点赞")
    @RequestMapping(value = "/up/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean<Integer> cancelNewsUp(@ApiParam(value = "token", required = true) @RequestParam String token,
                                 @ApiParam(value = "新闻ID", required = true) @RequestParam Integer news_id) {
        JsonBean<User> userJsonBean = userServiceFeign.getUser(token);
        if (!userJsonBean.isSuccess()) {
            return new JsonBean<>(new ErrorCode(userJsonBean.getError_code(), userJsonBean.getMsg()));
        }
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, newsUpService.deleteNewsUp(userJsonBean.getData().getId(), news_id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }

    @ApiOperation("判断用户是否点赞")
    @RequestMapping(value = "/up", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Boolean> userIsUp(@ApiParam(value = "token", required = true) @RequestParam String token,
                                      @ApiParam(value = "新闻ID", required = true) @RequestParam Integer news_id) {
        JsonBean<User> userJsonBean = userServiceFeign.getUser(token);
        if (!userJsonBean.isSuccess()) {
            return new JsonBean<>(new ErrorCode(userJsonBean.getError_code(), userJsonBean.getMsg()));
        }
        try {
            return new JsonBean<>(ErrorCode.SUCCESS, newsUpService.userIsUp(userJsonBean.getData().getId(), news_id));
        } catch (ErrorCodeException e) {
            return new JsonBean<>(e.getErrorCode());
        }
    }
}
