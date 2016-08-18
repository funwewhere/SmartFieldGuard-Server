package com.szty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szty.bean.CropInfo;
import com.szty.bean.CropPeriod;
import com.szty.bean.CropVariable;
import com.szty.bean.my.CropPlan;
import com.szty.mapper.CropInfoMapper;
import com.szty.mapper.my.CropMapper;
import com.szty.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private CropInfoMapper cropInfoMapper;
	
	@Autowired
	private CropMapper cropMapper;

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

}
