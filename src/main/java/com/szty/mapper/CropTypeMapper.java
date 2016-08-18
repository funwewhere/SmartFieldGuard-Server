package com.szty.mapper;

import com.szty.bean.CropType;

public interface CropTypeMapper {
    int deleteByPrimaryKey(String cropTypeNo);

    int insert(CropType record);

    int insertSelective(CropType record);

    CropType selectByPrimaryKey(String cropTypeNo);

    int updateByPrimaryKeySelective(CropType record);

    int updateByPrimaryKey(CropType record);
}