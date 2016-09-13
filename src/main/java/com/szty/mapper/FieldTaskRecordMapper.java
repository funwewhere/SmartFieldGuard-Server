package com.szty.mapper;

import com.szty.bean.FieldTaskRecord;

public interface FieldTaskRecordMapper {
    int deleteByPrimaryKey(Integer recordNo);

    int insert(FieldTaskRecord record);

    int insertSelective(FieldTaskRecord record);

    FieldTaskRecord selectByPrimaryKey(Integer recordNo);

    int updateByPrimaryKeySelective(FieldTaskRecord record);

    int updateByPrimaryKey(FieldTaskRecord record);
}