package com.szty.bean.my;

import java.util.List;

public class CropPlan {
	
	private VoCropInfo cropInfo;
	
	private List<VoCropTask> cropTaskList;

	public VoCropInfo getCropInfo() {
		return cropInfo;
	}

	public void setCropInfo(VoCropInfo cropInfo) {
		this.cropInfo = cropInfo;
	}

	public List<VoCropTask> getCropTaskList() {
		return cropTaskList;
	}

	public void setCropTaskList(List<VoCropTask> cropTaskList) {
		this.cropTaskList = cropTaskList;
	}
}
