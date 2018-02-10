package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.global.Constant;
import com.avatarcn.tourists.json.body.PostNews;
import com.avatarcn.tourists.json.response.PageResponse;
import com.avatarcn.tourists.json.response.news.NewsResponse;
import com.avatarcn.tourists.mapper.NewsCommentMapper;
import com.avatarcn.tourists.mapper.NewsMapper;
import com.avatarcn.tourists.mapper.NewsUpMapper;
import com.avatarcn.tourists.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by z1ven on 2018/1/17 14:44
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 60000, rollbackFor = Exception.class)
public class NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private NewsCommentMapper newsCommentMapper;

    @Autowired
    private NewsUpMapper newsUpMapper;

    @Autowired
    private OssService ossService;

    public News addNews(PostNews postNews) throws ErrorCodeException {
        News news = new News();
        news.setTitle(postNews.getTitle());
        String message="<head>"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> "
                + "<style>img{max-width: 100%; width:auto; height:auto;}</style>"
                + "</head>";
        news.setContent("<html>" + message + "<body>" + postNews.getContent() + "</body></html>");
        String url = ossService.copyFileTo(postNews.getImg_url(), Constant.TOURISTS_TMP_DIR, Constant.TOURISTS_NEWS_DIR);
        news.setImg(url);
        news.setRead_count(0);
        news.setWeb_content(postNews.getContent());
        news.setTime(new Date());
        newsMapper.insert(news);
        return news;
    }

    public int deleteNews(Integer id) throws ErrorCodeException {
        News news = newsMapper.selectById(id);
        if (news == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        //删除OSS里的关联文件
        ossService.delete(news.getImg());
        //删除评论和点赞
        newsCommentMapper.deleteByNewsId(id);
        newsUpMapper.deleteByNewsId(id);
        return newsMapper.delete(id);
    }

    public PageResponse<NewsResponse> selectPageNews(Integer offset, Integer pageSize) {
        List<News> newsList = newsMapper.selectPage(offset, pageSize);
        PageResponse<NewsResponse> newsResponsePageResponse = new PageResponse<>();
        List<NewsResponse> newsResponseList = new ArrayList<>();
        for (News news : newsList) {
            NewsResponse newsResponse = new NewsResponse();
            newsResponse.setId(news.getId());
            newsResponse.setTitle(news.getTitle());
            newsResponse.setImage(news.getImg());
            newsResponse.setRead_count(news.getRead_count());
            newsResponse.setComment_count(newsCommentMapper.countWithNewsId(news.getId()));
            newsResponse.setUp_count(newsUpMapper.countWithNewsId(news.getId()));
            newsResponse.setTime(news.getTime());
            newsResponseList.add(newsResponse);
        }
        newsResponsePageResponse.setItem(newsResponseList);
        newsResponsePageResponse.setOffset(offset);
        newsResponsePageResponse.setPageSize(pageSize);
        newsResponsePageResponse.setTotal(newsMapper.count());
        return newsResponsePageResponse;
    }

    /**
     * 阅读指定的新闻,阅读数+1并返回内容
     * @param id
     * @return
     * @throws ErrorCodeException
     */
    public News readNews(Integer id) throws ErrorCodeException {
        News news = newsMapper.selectById(id);
        if (news == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        news.setRead_count(news.getRead_count() + 1);
        newsMapper.update(news);
        return news;
    }

    public News selectOneNews(Integer id) throws ErrorCodeException {
        News news = newsMapper.selectById(id);
        if (news == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        return news;
    }

    public NewsResponse selectNewsResponse(Integer id) throws ErrorCodeException {
        News news = newsMapper.selectById(id);
        if (news == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        NewsResponse newsResponse = new NewsResponse();
        newsResponse.setId(news.getId());
        newsResponse.setTitle(news.getTitle());
        newsResponse.setImage(news.getImg());
        newsResponse.setRead_count(news.getRead_count());
        newsResponse.setComment_count(newsCommentMapper.countWithNewsId(news.getId()));
        newsResponse.setUp_count(newsUpMapper.countWithNewsId(news.getId()));
        newsResponse.setTime(news.getTime());
        return newsResponse;
    }

    public News editNews(Integer id, PostNews postNews) throws ErrorCodeException {
        News news = newsMapper.selectById(id);
        if (news == null) {
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        news.setTitle(postNews.getTitle());
        if (!postNews.getContent().equals(news.getWeb_content())) {
            String message="<head>"
                    + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> "
                    + "<style>img{max-width: 100%; width:auto; height:auto;}</style>"
                    + "</head>";
            news.setContent("<html>" + message + "<body>" + news.getContent() + "</body></html>");
            news.setWeb_content(postNews.getContent());
        }
        if (postNews.getImg_url() != null && !postNews.getImg_url().equals(news.getImg())) {
            ossService.delete(news.getImg());
            String url = ossService.copyFileTo(postNews.getImg_url(), Constant.TOURISTS_TMP_DIR, Constant.TOURISTS_NEWS_DIR);
            news.setImg(url);
        }
        news.setTime(new Date());
        newsMapper.update(news);
        return news;
    }
}
