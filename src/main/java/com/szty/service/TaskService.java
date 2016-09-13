package com.szty.service;

import com.szty.bean.FieldTaskRecord;
import com.szty.bean.my.CropPlan;
import com.szty.exception.CustomException;

public interface TaskService {

	public CropPlan download(String cropNo);

	public void recordFieldTask(FieldTaskRecord fieldTaskRecord) throws CustomException;

}
