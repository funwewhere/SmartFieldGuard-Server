package com.szty.bean.my;

import java.util.Date;

import com.szty.enums.TaskType;

public class VoFieldRecord {
	private String recordNo;
	
	private String fieldNo;
	
	private String cropNo;
	
	private String taskNo;

    private String taskName;

    private String taskFormula;

    private String lastTask;

    private TaskType taskType;

//    private Integer start;
//
//    private Integer useDays;

    private String periodNo;
    
    private String attention;
    
    private Date finishTime;

	public String getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}

	public String getFieldNo() {
		return fieldNo;
	}

	public void setFieldNo(String fieldNo) {
		this.fieldNo = fieldNo;
	}

	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskFormula() {
		return taskFormula;
	}

	public void setTaskFormula(String taskFormula) {
		this.taskFormula = taskFormula;
	}

	public String getLastTask() {
		return lastTask;
	}

	public void setLastTask(String lastTask) {
		this.lastTask = lastTask;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public String getPeriodNo() {
		return periodNo;
	}

	public void setPeriodNo(String periodNo) {
		this.periodNo = periodNo;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getCropNo() {
		return cropNo;
	}

	public void setCropNo(String cropNo) {
		this.cropNo = cropNo;
	}
    
}
