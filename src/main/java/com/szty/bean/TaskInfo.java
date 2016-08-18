package com.szty.bean;

import com.szty.enums.TaskType;

public class TaskInfo {
    private String taskNo;

    private String cropNo;

    private String taskName;

    private String taskFormula;

    private String lastTask;

    private TaskType taskType;

    private Integer start;

    private Integer useDays;

    private String periodNo;

    private String attention;

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo == null ? null : taskNo.trim();
    }

    public String getCropNo() {
        return cropNo;
    }

    public void setCropNo(String cropNo) {
        this.cropNo = cropNo == null ? null : cropNo.trim();
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getTaskFormula() {
        return taskFormula;
    }

    public void setTaskFormula(String taskFormula) {
        this.taskFormula = taskFormula == null ? null : taskFormula.trim();
    }

    public String getLastTask() {
        return lastTask;
    }

    public void setLastTask(String lastTask) {
        this.lastTask = lastTask == null ? null : lastTask.trim();
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getUseDays() {
        return useDays;
    }

    public void setUseDays(Integer useDays) {
        this.useDays = useDays;
    }

    public String getPeriodNo() {
        return periodNo;
    }

    public void setPeriodNo(String periodNo) {
        this.periodNo = periodNo == null ? null : periodNo.trim();
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention == null ? null : attention.trim();
    }
}