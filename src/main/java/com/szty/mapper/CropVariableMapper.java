package com.szty.mapper;

import com.szty.bean.CropVariable;
import org.apache.ibatis.annotations.Param;

public interface CropVariableMapper {
    int deleteByPrimaryKey(@Param("cropNo") String cropNo, @Param("variableName") String variableName, @Param("periodNo") String periodNo);

    int insert(CropVariable record);

    int insertSelective(CropVariable record);

    CropVariable selectByPrimaryKey(@Param("cropNo") String cropNo, @Param("variableName") String variableName, @Param("periodNo") String periodNo);

    int updateByPrimaryKeySelective(CropVariable record);

    int updateByPrimaryKey(CropVariable record);
}