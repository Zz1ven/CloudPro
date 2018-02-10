package com.avatarcn.tourists.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

/**
 * Created by z1ven on 2018/1/25 17:39
 */
@JsonIgnoreProperties(value = {"handler"})
public class Feedback {
    private int id;
    private int fk_tb_user_id;
    private int fk_tb_feedback_type_id;
    private String content;
    private String email;
    private String phone;
    private Date time;

    private FeedbackType feedbackType;
    private List<FeedbackImg> feedbackImgList;
    private List<FeedbackReply> feedbackReplyList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_tb_user_id() {
        return fk_tb_user_id;
    }

    public void setFk_tb_user_id(int fk_tb_user_id) {
        this.fk_tb_user_id = fk_tb_user_id;
    }

    public int getFk_tb_feedback_type_id() {
        return fk_tb_feedback_type_id;
    }

    public void setFk_tb_feedback_type_id(int fk_tb_feedback_type_id) {
        this.fk_tb_feedback_type_id = fk_tb_feedback_type_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public FeedbackType getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(FeedbackType feedbackType) {
        this.feedbackType = feedbackType;
    }

    public List<FeedbackImg> getFeedbackImgList() {
        return feedbackImgList;
    }

    public void setFeedbackImgList(List<FeedbackImg> feedbackImgList) {
        this.feedbackImgList = feedbackImgList;
    }

    public List<FeedbackReply> getFeedbackReplyList() {
        return feedbackReplyList;
    }

    public void setFeedbackReplyList(List<FeedbackReply> feedbackReplyList) {
        this.feedbackReplyList = feedbackReplyList;
    }
}
