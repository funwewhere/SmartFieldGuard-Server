package com.szty.mapper;

import com.szty.bean.FieldInfo;

public interface FieldInfoMapper {
    int deleteByPrimaryKey(String fieldNo);

    int insert(FieldInfo record);

    int insertSelective(FieldInfo record);

    FieldInfo selectByPrimaryKey(String fieldNo);

    int updateByPrimaryKeySelective(FieldInfo record);

    int updateByPrimaryKey(FieldInfo record);
}