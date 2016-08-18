package com.szty.mapper;

import com.szty.bean.CropPeriod;
import org.apache.ibatis.annotations.Param;

public interface CropPeriodMapper {
    int deleteByPrimaryKey(@Param("periodNo") String periodNo, @Param("cropNo") String cropNo);

    int insert(CropPeriod record);

    int insertSelective(CropPeriod record);

    CropPeriod selectByPrimaryKey(@Param("periodNo") String periodNo, @Param("cropNo") String cropNo);

    int updateByPrimaryKeySelective(CropPeriod record);

    int updateByPrimaryKey(CropPeriod record);
}