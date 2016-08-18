package com.szty.bean;

public class CropInfo {
    private String cropNo;

    private String cropTypeNo;

    private String cropName;

    private String imageUrl;

    private String intro;

    private String temperature;

    private String light;

    private String water;

    private String soil;

    private Integer growDays;

    public String getCropNo() {
        return cropNo;
    }

    public void setCropNo(String cropNo) {
        this.cropNo = cropNo == null ? null : cropNo.trim();
    }

    public String getCropTypeNo() {
        return cropTypeNo;
    }

    public void setCropTypeNo(String cropTypeNo) {
        this.cropTypeNo = cropTypeNo == null ? null : cropTypeNo.trim();
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName == null ? null : cropName.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? null : temperature.trim();
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light == null ? null : light.trim();
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water == null ? null : water.trim();
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil == null ? null : soil.trim();
    }

    public Integer getGrowDays() {
        return growDays;
    }

    public void setGrowDays(Integer growDays) {
        this.growDays = growDays;
    }
}