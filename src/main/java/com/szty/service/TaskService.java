package com.szty.service;

import java.util.List;

import com.szty.bean.FieldTaskRecord;
import com.szty.bean.my.CropPlan;
import com.szty.bean.my.FieldPlanRecord;
import com.szty.exception.CustomException;

public interface TaskService {

	public CropPlan download(String cropNo);

	public void recordFieldTask(FieldTaskRecord fieldTaskRecord) throws CustomException;

	public List<FieldPlanRecord> getFieldRecord(String fieldNo, Integer pageIndex, Integer pageCount);

}
