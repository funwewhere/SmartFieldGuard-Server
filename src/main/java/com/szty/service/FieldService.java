package com.szty.service;

import java.util.List;

import com.szty.bean.FieldInfo;
import com.szty.bean.my.DeviceRequest;
import com.szty.bean.my.FieldHistoryCriteria;
import com.szty.bean.my.VoFeild;
import com.szty.bean.my.VoFieldData;


public interface FieldService {

 	public void addField(FieldInfo fieldInfo) throws Exception;

	public boolean updateFieldData(DeviceRequest deviceData) throws Exception;

	public List<VoFeild> userFieldList(String userId);

	public void setFieldInfo(FieldInfo fieldInfo);

	public VoFieldData getNewestData(String fieldNo, String userId);

	public List<VoFieldData> analyzeHistoryData(FieldHistoryCriteria criteria);

	public void getDeviceNewestData(String userId, String fieldNo);

}
