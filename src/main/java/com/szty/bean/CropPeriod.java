package com.szty.bean;

public class CropPeriod {
    private String periodNo;

    private String cropNo;

    private String periodName;

    public String getPeriodNo() {
        return periodNo;
    }

    public void setPeriodNo(String periodNo) {
        this.periodNo = periodNo == null ? null : periodNo.trim();
    }

    public String getCropNo() {
        return cropNo;
    }

    public void setCropNo(String cropNo) {
        this.cropNo = cropNo == null ? null : cropNo.trim();
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName == null ? null : periodName.trim();
    }
}