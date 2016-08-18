package com.szty.mapper;

import com.szty.bean.WaitingForInform;

public interface WaitingForInformMapper {
    int deleteByPrimaryKey(Integer informFlowNo);

    int insert(WaitingForInform record);

    int insertSelective(WaitingForInform record);

    WaitingForInform selectByPrimaryKey(Integer informFlowNo);

    int updateByPrimaryKeySelective(WaitingForInform record);

    int updateByPrimaryKey(WaitingForInform record);
}