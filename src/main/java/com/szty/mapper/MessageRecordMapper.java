package com.szty.mapper;

import com.szty.bean.MessageRecord;

public interface MessageRecordMapper {
    int deleteByPrimaryKey(String messageNo);

    int insert(MessageRecord record);

    int insertSelective(MessageRecord record);

    MessageRecord selectByPrimaryKey(String messageNo);

    int updateByPrimaryKeySelective(MessageRecord record);

    int updateByPrimaryKeyWithBLOBs(MessageRecord record);

    int updateByPrimaryKey(MessageRecord record);
}