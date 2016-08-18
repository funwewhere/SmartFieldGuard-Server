package com.szty.bean;

import java.util.Date;

public class FieldHistory {
    private Integer fieldHistoryNo;

    private String fieldNo;

    private String cropNo;

    private Date recordTime;

    private Double soilT;

    private Double soilW;

    private Double airT;

    private Double airW;

    private Double light;

    private Double co2;

    public Integer getFieldHistoryNo() {
        return fieldHistoryNo;
    }

    public void setFieldHistoryNo(Integer fieldHistoryNo) {
        this.fieldHistoryNo = fieldHistoryNo;
    }

    public String getFieldNo() {
        return fieldNo;
    }

    public void setFieldNo(String fieldNo) {
        this.fieldNo = fieldNo == null ? null : fieldNo.trim();
    }

    public String getCropNo() {
        return cropNo;
    }

    public void setCropNo(String cropNo) {
        this.cropNo = cropNo == null ? null : cropNo.trim();
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
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

    public Double getLight() {
        return light;
    }

    public void setLight(Double light) {
        this.light = light;
    }

    public Double getCo2() {
        return co2;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }
}