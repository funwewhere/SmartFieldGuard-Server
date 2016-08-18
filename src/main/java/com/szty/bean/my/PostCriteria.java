package com.szty.bean.my;

import java.util.Date;

import com.szty.enums.PostStatus;
import com.szty.enums.PostType;

public class PostCriteria {
	private Integer dataStart;

	private Integer pageIndex;
    
    private Integer pageCount;
	
	private String userId;
	
    private PostType type;

    private String parentArea;
    
    private PostStatus status;
    
    private Date lastDate;

	public Integer getDataStart() {
		return dataStart;
	}

	public void setDataStart(Integer dataStart) {
		this.dataStart = dataStart;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getParentArea() {
		return parentArea;
	}

	public void setParentArea(String parentArea) {
		this.parentArea = parentArea;
	}

	public PostType getType() {
		return type;
	}

	public void setType(PostType type) {
		this.type = type;
	}

	public PostStatus getStatus() {
		return status;
	}

	public void setStatus(PostStatus status) {
		this.status = status;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
    
}
