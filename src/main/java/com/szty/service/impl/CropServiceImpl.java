package com.szty.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szty.bean.CropType;
import com.szty.bean.my.VoCropInfoSimple;
import com.szty.bean.my.VoCropType;
import com.szty.mapper.CropTypeMapper;
import com.szty.mapper.my.CropMapper;
import com.szty.service.CropService;

@Service
public class CropServiceImpl implements CropService {
	
	private static Logger log = Logger.getLogger(CropServiceImpl.class);
	
	@Autowired
	private CropMapper cropMapper;
	
	@Autowired
	private CropTypeMapper cropTypeMapper;

	@Override
	public List<VoCropType> getSubTypes(String cropTypeNo) {
		List<VoCropType> cropTypeList = cropMapper.getSubCropTypes(cropTypeNo);
		return cropTypeList;
	}

	@Override
	public List<VoCropInfoSimple> getCropListSimple(String cropTypeNo) {
		CropType cropType = cropTypeMapper.selectByPrimaryKey(cropTypeNo);
		if (cropType == null || !cropType.getIsLast()) {
			log.info("cropType wrong or not last");
			return null;
		}
		List<VoCropInfoSimple> cropList = cropMapper.getCropListSimple(cropTypeNo);
		return cropList;
	}

}
