package com.szty.bean.my;

import java.util.Date;

import com.szty.enums.TimeScope;

public class FieldHistoryCriteria {
	
	private String userId;

	private String fieldNo;
	
	private String cropNo;
	
	private TimeScope timeScope;
	
	private Integer number;
	
	private Date startTime;
	
	private Date endTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCropNo() {
		return cropNo;
	}

	public void setCropNo(String cropNo) {
		this.cropNo = cropNo;
	}

	public TimeScope getTimeScope() {
		return timeScope;
	}

	public void setTimeScope(TimeScope timeScope) {
		this.timeScope = timeScope;
	}

	public String getFieldNo() {
		return fieldNo;
	}

	public void setFieldNo(String fieldNo) {
		this.fieldNo = fieldNo;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
}
