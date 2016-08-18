package com.szty.mapper;

import com.szty.bean.CropInfo;

public interface CropInfoMapper {
    int deleteByPrimaryKey(String cropNo);

    int insert(CropInfo record);

    int insertSelective(CropInfo record);

    CropInfo selectByPrimaryKey(String cropNo);

    int updateByPrimaryKeySelective(CropInfo record);

    int updateByPrimaryKey(CropInfo record);
}