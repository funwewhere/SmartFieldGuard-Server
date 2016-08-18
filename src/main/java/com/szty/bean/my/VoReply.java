package com.szty.bean.my;

import java.util.Date;

/**
 * @author fwwer
 *
 */
public class VoReply {
	private String postNo;

    private Integer replySn;

    private String userId;

    private String username;
    
    private String headImage;
    
    private String content;
    
    private String images;

    private Integer agreeNum;

    private Date replyDate;

    private String agreeUsers;

	public String getPostNo() {
		return postNo;
	}

	public void setPostNo(String postNo) {
		this.postNo = postNo;
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
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
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

	public String getAgreeUsers() {
		return agreeUsers;
	}

	public void setAgreeUsers(String agreeUsers) {
		this.agreeUsers = agreeUsers;
	}
    
}
