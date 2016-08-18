package com.szty.mapper;

import com.szty.bean.SysTableKey;

public interface SysTableKeyMapper {
    int deleteByPrimaryKey(String tableName);

    int insert(SysTableKey record);

    int insertSelective(SysTableKey record);

    SysTableKey selectByPrimaryKey(String tableName);

    int updateByPrimaryKeySelective(SysTableKey record);

    int updateByPrimaryKey(SysTableKey record);
}