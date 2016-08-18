package com.szty.bean;

import java.util.Date;

public class ReplyInfo {
    private String postNo;

    private Integer replySn;

    private String userId;

    private String images;

    private Integer agreeNum;

    private Date replyDate;

    private String content;

    private String agreeUsers;

    public String getPostNo() {
        return postNo;
    }

    public void setPostNo(String postNo) {
        this.postNo = postNo == null ? null : postNo.trim();
    }

    public Integer getReplySn() {
        return replySn;
    }

    public void setReplySn(Integer replySn) {
        this.replySn = replySn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public Integer getAgreeNum() {
        return agreeNum;
    }

    public void setAgreeNum(Integer agreeNum) {
        this.agreeNum = agreeNum;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getAgreeUsers() {
        return agreeUsers;
    }

    public void setAgreeUsers(String agreeUsers) {
        this.agreeUsers = agreeUsers == null ? null : agreeUsers.trim();
    }
}