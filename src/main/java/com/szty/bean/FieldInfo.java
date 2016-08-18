package com.szty.bean;

import com.szty.enums.FieldStatus;
import java.util.Date;

public class FieldInfo {
    private String fieldNo;

    private String userId;

    private String fieldName;

    private Double fieldArea;

    private String deviceMac;

    private Double longitude;

    private Double latitude;

    private String cropNo;

    private Double soilT;

    private Double soilW;

    private Double airT;

    private Double airW;

    private Double co2;

    private Double light;

    private Date lastTime;

    private FieldStatus status;

    private Date startTime;

    public String getFieldNo() {
        return fieldNo;
    }

    public void setFieldNo(String fieldNo) {
        this.fieldNo = fieldNo == null ? null : fieldNo.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public Double getFieldArea() {
        return fieldArea;
    }

    public void setFieldArea(Double fieldArea) {
        this.fieldArea = fieldArea;
    }

    public String getDeviceMac() {
        return deviceMac;
    }

    public void setDeviceMac(String deviceMac) {
        this.deviceMac = deviceMac == null ? null : deviceMac.trim();
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getCropNo() {
        return cropNo;
    }

    public void setCropNo(String cropNo) {
        this.cropNo = cropNo == null ? null : cropNo.trim();
    }

    public Double getSoilT() {
        return soilT;
    }

    public void setSoilT(Double soilT) {
        this.soilT = soilT;
    }

    public Double getSoilW() {
        return soilW;
    }

    public void setSoilW(Double soilW) {
        this.soilW = soilW;
    }

    public Double getAirT() {
        return airT;
    }

    public void setAirT(Double airT) {
        this.airT = airT;
    }

    public Double getAirW() {
        return airW;
    }

    public void setAirW(Double airW) {
        this.airW = airW;
    }

    public Double getCo2() {
        return co2;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }

    public Double getLight() {
        return light;
    }

    public void setLight(Double light) {
        this.light = light;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
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