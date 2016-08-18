package com.szty.bean.my;

import java.util.List;

import com.szty.enums.TaskType;

public class VoCropTask {
	private String taskNo;

    private String taskName;

    private String taskFormula;

    private String lastTask;

    private TaskType taskType;

    private Integer start;

    private Integer useDays;

    private String periodNo;
    
    private String attention;
    
    private List<TaskTriggerSimple> taskTriggerList;
    
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
		this.periodNo = periodNo;
	}

	public List<TaskTriggerSimple> getTaskTriggerList() {
		return taskTriggerList;
	}

	public void setTaskTriggerList(List<TaskTriggerSimple> taskTriggerList) {
		this.taskTriggerList = taskTriggerList;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}
    
}