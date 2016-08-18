package com.szty.bean.my;

import java.util.Date;

import com.szty.enums.FieldStatus;

public class VoFeild {
	private String fieldNo;

    private String userId;

    private String fieldName;

    private Float fieldArea;

    private String deviceMac;

    private Float longitude;

    private Float latitude;

    private String cropNo;
    
    private FieldStatus status;
    
    private Date startTime;

	public String getFieldNo() {
		return fieldNo;
	}

	public void setFieldNo(String fieldNo) {
		this.fieldNo = fieldNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Float getFieldArea() {
		return fieldArea;
	}

	public void setFieldArea(Float fieldArea) {
		this.fieldArea = fieldArea;
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public String getCropNo() {
		return cropNo;
	}

	public void setCropNo(String cropNo) {
		this.cropNo = cropNo;
	}

	public FieldStatus getStatus() {
		return status;
	}

	public void setStatus(FieldStatus status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
    
}