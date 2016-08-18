package com.szty.bean;

public class TaskTrigger {
    private String taskNo;

    private String triggerFormula;

    private String triggerThan;

    private String triggerValue;

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo == null ? null : taskNo.trim();
    }

    public String getTriggerFormula() {
        return triggerFormula;
    }

    public void setTriggerFormula(String triggerFormula) {
        this.triggerFormula = triggerFormula == null ? null : triggerFormula.trim();
    }

    public String getTriggerThan() {
        return triggerThan;
    }

    public void setTriggerThan(String triggerThan) {
        this.triggerThan = triggerThan == null ? null : triggerThan.trim();
    }

    public String getTriggerValue() {
        return triggerValue;
    }

    public void setTriggerValue(String triggerValue) {
        this.triggerValue = triggerValue == null ? null : triggerValue.trim();
    }
}