package com.avatarcn.tourists.json.response.news;

import java.util.List;

/**
 * Created by z1ven on 2018/1/17 17:24
 */
public class NewsCommentAndUpResponse {
    private List<NewsCommentResponse> comments;
    private int offset;
    private int pageSize;
    private int commentCount;
    private int upCount;

    public List<NewsCommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<NewsCommentResponse> comments) {
        this.comments = comments;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getUpCount() {
        return upCount;
    }

    public void setUpCount(int upCount) {
        this.upCount = upCount;
    }
}
