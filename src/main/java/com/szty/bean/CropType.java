package com.szty.bean;

public class CropType {
    private String cropTypeNo;

    private String cropTypeName;

    private String parentType;

    private Boolean isLast;

    private String imageUrl;

    public String getCropTypeNo() {
        return cropTypeNo;
    }

    public void setCropTypeNo(String cropTypeNo) {
        this.cropTypeNo = cropTypeNo == null ? null : cropTypeNo.trim();
    }

    public String getCropTypeName() {
        return cropTypeName;
    }

    public void setCropTypeName(String cropTypeName) {
        this.cropTypeName = cropTypeName == null ? null : cropTypeName.trim();
    }

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType == null ? null : parentType.trim();
    }

    public Boolean getIsLast() {
        return isLast;
    }

    public void setIsLast(Boolean isLast) {
        this.isLast = isLast;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }
}