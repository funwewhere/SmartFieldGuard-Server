package com.szty.mapper;

import com.szty.bean.FieldHistory;

public interface FieldHistoryMapper {
    int deleteByPrimaryKey(Integer fieldHistoryNo);

    int insert(FieldHistory record);

    int insertSelective(FieldHistory record);

    FieldHistory selectByPrimaryKey(Integer fieldHistoryNo);

    int updateByPrimaryKeySelective(FieldHistory record);

    int updateByPrimaryKey(FieldHistory record);
}