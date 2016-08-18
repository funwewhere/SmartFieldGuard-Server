package com.szty.bean.my;

public class TaskTriggerSimple {
    private String triggerFormula;

    private String triggerThan;

    private String triggerValue;

    public String getTriggerFormula() {
        return triggerFormula;
    }

    public void setTriggerFormula(String triggerFormula) {
        this.triggerFormula = triggerFormula == null ? null : triggerFormula.trim();
    }

    public String getTriggerValue() {
        return triggerValue;
    }

    public void setTriggerValue(String triggerValue) {
        this.triggerValue = triggerValue == null ? null : triggerValue.trim();
    }

	public String getTriggerThan() {
		return triggerThan;
	}

	public void setTriggerThan(String triggerThan) {
		this.triggerThan = triggerThan;
	}
}
