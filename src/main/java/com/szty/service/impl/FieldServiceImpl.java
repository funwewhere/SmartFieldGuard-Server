package com.szty.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.szty.bean.FieldInfo;
import com.szty.bean.UserInfo;
import com.szty.bean.my.DeviceRequest;
import com.szty.bean.my.FieldHistoryCriteria;
import com.szty.bean.my.VoFeild;
import com.szty.bean.my.VoFieldData;
import com.szty.enums.FieldStatus;
import com.szty.enums.Table;
import com.szty.mapper.FieldInfoMapper;
import com.szty.mapper.UserInfoMapper;
import com.szty.mapper.my.FieldMapper;
import com.szty.service.FieldService;
import com.szty.socket.handler.SocketHandler;
import com.szty.util.SystemUtil;

@Service
public class FieldServiceImpl implements FieldService {
	
	private static Logger log = Logger.getLogger(FieldServiceImpl.class);
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private FieldInfoMapper fieldInfoMapper;
	
	@Autowired
	private FieldMapper fieldMapper;
	
	@Autowired
	private SystemUtil systemUtil;
	
	@Autowired
	private SocketHandler socketHandler;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addField(FieldInfo fieldInfo) throws Exception {
		String userId = fieldInfo.getUserId();
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
		if (userInfo == null) {
			log.info("user id [" + userId + "] error");
			return;
		}
		String deviceMac = fieldInfo.getDeviceMac();
	    if (StringUtils.isBlank(deviceMac)) {
	    	log.info("deviceMac is null");
	    	return;
	    }
	    
	    int num = fieldMapper.getFieldCount(null, userId, deviceMac);
	    if(num == 0) {
		    String fieldNo = systemUtil.gerneratorKey(Table.FIELD);
		    fieldInfo.setFieldNo(fieldNo);
		    fieldInfo.setFieldName("未设置");
		    fieldInfo.setStatus(FieldStatus.InUse);
		    fieldInfo.setLastTime(new Date());
		    fieldInfoMapper.insert(fieldInfo);
	    } else if (num > 1) {
	    	throw new Exception("field userId [" + userId +"] mac [" + deviceMac + "] have " + num);
	    }
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateFieldData(DeviceRequest deviceData) throws Exception {
		String userId = deviceData.getUserId();
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
		if (userInfo == null) {
			log.info("user id [" + userId + "] error");
			return false;
		}
		String mac = deviceData.getMac();
		if (StringUtils.isBlank(mac)) {
			log.info("device mac is null");
			return false;
		}
		
		fieldMapper.RecordFieldData(userId, mac);
		deviceData.setNowDate(new Date());
		int num = fieldMapper.updateFieldData(deviceData);
		if(num != 1){
			throw new Exception("update fielddata null");
		}
		return true;
	}

	@Override
	public List<VoFeild> userFieldList(String userId) {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
		if (userInfo == null) {
			log.info("user id [" + userId + "] error");
			return null;
		}
		List<VoFeild> feildList = fieldMapper.getFieldList(userId);
		return feildList;
	}

	@Override
	public void setFieldInfo(FieldInfo fieldInfo) {
		String fieldNo = fieldInfo.getFieldNo();
		String userId = fieldInfo.getUserId();
		if(StringUtils.isBlank(fieldNo) || StringUtils.isBlank(userId)){
			return;
		}
		int num = fieldMapper.getFieldCount(fieldNo, userId, null);
		if (num != 1) {
			return;
		}
		fieldInfo.setLastTime(null);
		fieldInfo.setDeviceMac(null);
		fieldInfoMapper.updateByPrimaryKeySelective(fieldInfo);
	}

	@Override
	public VoFieldData getNewestData(String fieldNo, String userId) {
		VoFieldData fieldData = fieldMapper.getFieldData(fieldNo, userId);
		if (fieldData != null && !StringUtils.isBlank(fieldData.getDeviceMac())) {
			socketHandler.sendMessage("device_" + fieldData.getDeviceMac(), "\nnewData\n");
		}
		return fieldData;
	}

	@Override
	public List<VoFieldData> analyzeHistoryData(FieldHistoryCriteria criteria) {
		String userId = criteria.getUserId();
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
		if (userInfo == null) {
			log.info("user id [" + userId + "] error");
			return null;
		}
		String fieldNo = criteria.getFieldNo();
		if (fieldNo == null) {
			log.info("fieldNo is null");
			return null;
		}
		int number = fieldMapper.getFieldCount(fieldNo, userId, null);
		if(number < 1){
			return null;
		}
		
		number = criteria.getNumber();
		if (number < 1) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		Date nowTime = new Date();
		criteria.setEndTime(nowTime);
		calendar.setTime(nowTime);
		int timeField = 0;
		int subTimeField = 0;
		switch (criteria.getTimeScope()) {
			case Day: timeField = Calendar.DAY_OF_YEAR; subTimeField = Calendar.HOUR_OF_DAY; break;
			case Week: timeField = Calendar.WEEK_OF_YEAR; subTimeField = Calendar.DAY_OF_YEAR; break;
			case Month: timeField = Calendar.MONTH; subTimeField = Calendar.DAY_OF_YEAR; break;
			case Year: timeField = Calendar.YEAR; subTimeField = Calendar.MONTH; break;
			default:return null;
		}
		List<VoFieldData> statisticsFieldHistoryData = statisticsFieldHistoryData(criteria, timeField, subTimeField, number);
		return statisticsFieldHistoryData;
	}
	
	private List<VoFieldData> statisticsFieldHistoryData(FieldHistoryCriteria criteria, int timeField, int subTimeField, int number) {
		Calendar calendar = Calendar.getInstance();
		Date nowTime = new Date();
		criteria.setEndTime(nowTime);
		calendar.setTime(nowTime);
		calendar.add(timeField, -number);
		criteria.setStartTime(calendar.getTime());
		List<VoFieldData> fieldDatas = fieldMapper.getFieldHistoryDatas(criteria);
		if (fieldDatas == null || fieldDatas.isEmpty()) {
			return null;
		}
		int last = 0;
		int index = 0;
		List<VoFieldData> newfieldDatas = new ArrayList<VoFieldData>();
		VoFieldData data = new VoFieldData();
		for(int i = 0, len = fieldDatas.size(); i < len ; ++i){
			calendar.setTime(fieldDatas.get(i).getRecordTime());
			index = calendar.get(subTimeField);
			if (data.isEmpty()) {
				last = index;
			}
			if (index != last) {
				last = index;
				data.calculateAverageValue();
				newfieldDatas.add(data);
				data = new VoFieldData();
			}
			data.addFieldData(fieldDatas.get(i));
		}
		data.calculateAverageValue();
		newfieldDatas.add(data);
		return newfieldDatas;
	}

	@Override
	public void getDeviceNewestData(String userId, String fieldNo) {
		FieldInfo fieldInfo = fieldInfoMapper.selectByPrimaryKey(fieldNo);
		if(!fieldInfo.getUserId().equals(userId)){
			return;
		}
		socketHandler.sendMessage("device_" + fieldInfo.getDeviceMac(), "newData\n");
	}

}
