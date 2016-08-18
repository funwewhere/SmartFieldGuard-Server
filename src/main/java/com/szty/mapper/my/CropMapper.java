package com.szty.mapper.my;

import java.util.List;

import com.szty.bean.CropPeriod;
import com.szty.bean.CropVariable;
import com.szty.bean.my.CropPlan;
import com.szty.bean.my.VoCropInfo;
import com.szty.bean.my.VoCropInfoSimple;
import com.szty.bean.my.VoCropTask;
import com.szty.bean.my.VoCropType;

public interface CropMapper {

	public List<VoCropType> getSubCropTypes(String cropTypeNo);

	public List<VoCropInfoSimple> getCropListSimple(String cropTypeNo);

	public CropPlan getCropPlan(String cropNo);
	
	public VoCropInfo getCropInfo(String cropNo);
	
	public List<VoCropTask> getCropTaskList(String cropNo);

	public List<CropVariable> getCropVariables(String cropNo);

	public List<CropPeriod> getCropPeriods(String cropNo);
}
