package com.szty.mapper;

import com.szty.bean.TaskInfo;

public interface TaskInfoMapper {
    int deleteByPrimaryKey(String taskNo);

    int insert(TaskInfo record);

    int insertSelective(TaskInfo record);

    TaskInfo selectByPrimaryKey(String taskNo);

    int updateByPrimaryKeySelective(TaskInfo record);

    int updateByPrimaryKey(TaskInfo record);
}