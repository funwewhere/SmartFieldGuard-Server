package com.szty.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szty.bean.CropInfo;
import com.szty.bean.CropPeriod;
import com.szty.bean.CropVariable;
import com.szty.bean.FieldTaskRecord;
import com.szty.bean.TaskInfo;
import com.szty.bean.my.CropPlan;
import com.szty.bean.my.FieldPlanRecord;
import com.szty.bean.my.VoCropInfo;
import com.szty.bean.my.VoFieldRecord;
import com.szty.enums.TaskType;
import com.szty.exception.CustomException;
import com.szty.mapper.CropInfoMapper;
import com.szty.mapper.FieldTaskRecordMapper;
import com.szty.mapper.TaskInfoMapper;
import com.szty.mapper.my.CropMapper;
import com.szty.mapper.my.TaskMapper;
import com.szty.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private CropInfoMapper cropInfoMapper;
	
	@Autowired
	private CropMapper cropMapper;
	
	@Autowired
	private TaskInfoMapper taskInfoMapper;
	
	@Autowired
	private FieldTaskRecordMapper recordMapper;
	
	@Autowired
	private TaskMapper taskMapper;

	@Override
	public CropPlan download(String cropNo) {
		CropInfo cropInfo = cropInfoMapper.selectByPrimaryKey(cropNo);
		if (cropInfo == null) {
			return null;
		}
		CropPlan cropPlan = cropMapper.getCropPlan(cropNo);
		List<CropVariable> cropVariableList = cropMapper.getCropVariables(cropNo);
		List<CropPeriod> cropPeriodList = cropMapper.getCropPeriods(cropNo);
		Map<String, String> variableList = new HashMap<String, String>();
		for (CropPeriod cropPeriod: cropPeriodList) {
			variableList.put(cropPeriod.getPeriodNo(), cropPeriod.getPeriodName());
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (CropVariable cropVariable: cropVariableList) {
			String periodNo = cropVariable.getPeriodNo();
			if(!"all".equals(periodNo)) {
				stringBuilder.append(periodNo);
				stringBuilder.append("_");
			}
			stringBuilder.append(cropVariable.getVariableName());
			variableList.put(stringBuilder.toString(), cropVariable.getVariableValue());
			stringBuilder.delete(0, stringBuilder.length());
		}
		cropPlan.getCropInfo().setCropVariableList(variableList);
		return cropPlan;
	}

	@Override
	public void recordFieldTask(FieldTaskRecord fieldTaskRecord) throws CustomException {
		fieldTaskRecord.setFinishTime(new Date());
		TaskInfo taskInfo = taskInfoMapper.selectByPrimaryKey(fieldTaskRecord.getTaskNo());
		if (taskInfo == null) {
			throw new CustomException("invalid taskNo");
		}
		if(StringUtils.isBlank(fieldTaskRecord.getVariables())){
			fieldTaskRecord.setVariables(null);
		}
		fieldTaskRecord.setCropNo(taskInfo.getCropNo());
		recordMapper.insert(fieldTaskRecord);
	}

	@Override
	public List<FieldPlanRecord> getFieldRecord(String fieldNo, Integer pageIndex, Integer pageCount) {
		List<VoFieldRecord> fieldRecordList = taskMapper.getFieldRecords(fieldNo);
		List<FieldPlanRecord> historyRecords = new ArrayList<FieldPlanRecord>();
		FieldPlanRecord fieldPlanRecord = new FieldPlanRecord();
		
		for(int i=0, length=fieldRecordList.size(); i<length; ++i){
			VoFieldRecord fieldRecord = fieldRecordList.get(i);
			if (fieldRecord.getTaskType() == TaskType.Start || i == length-1) {
				if (fieldPlanRecord.recordSize() > 0) {
					if (fieldRecord.getTaskType() != TaskType.Start && i == length-1) {
						fieldPlanRecord.addRecord(fieldRecord);
					}
					String cropNo = fieldPlanRecord.getFieldRecords().get(0).getCropNo();
					VoCropInfo cropInfo = cropMapper.getCropInfo(cropNo);
					fieldPlanRecord.setCropNo(cropNo);
					fieldPlanRecord.setCropName(cropInfo.getCropName());
					fieldPlanRecord.setCropTpyeNo(cropInfo.getCropTpyeNo());
					fieldPlanRecord.setImageUrl(cropInfo.getImageUrl());
					historyRecords.add(fieldPlanRecord);
					fieldPlanRecord = new FieldPlanRecord();
					continue;
				}
			}
			fieldPlanRecord.addRecord(fieldRecord);
		}
		return historyRecords;
	}

}
