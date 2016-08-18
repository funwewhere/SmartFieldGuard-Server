package com.szty.mapper;

import com.szty.bean.TaskTrigger;
import org.apache.ibatis.annotations.Param;

public interface TaskTriggerMapper {
    int deleteByPrimaryKey(@Param("taskNo") String taskNo, @Param("triggerFormula") String triggerFormula);

    int insert(TaskTrigger record);

    int insertSelective(TaskTrigger record);

    TaskTrigger selectByPrimaryKey(@Param("taskNo") String taskNo, @Param("triggerFormula") String triggerFormula);

    int updateByPrimaryKeySelective(TaskTrigger record);

    int updateByPrimaryKey(TaskTrigger record);
}