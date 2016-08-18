package com.szty.mapper.my;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.szty.bean.my.DeviceRequest;
import com.szty.bean.my.FieldHistoryCriteria;
import com.szty.bean.my.VoFeild;
import com.szty.bean.my.VoFieldData;

public interface FieldMapper {

	public int updateFieldData(DeviceRequest deviceData);

	public int RecordFieldData(@Param("userId")String userId, @Param("mac")String mac);

	public int getFieldCount(@Param("fieldNo")String fieldNo, @Param("userId")String userId, @Param("mac")String mac);

	public List<VoFeild> getFieldList(String userId);

	public VoFieldData getFieldData(@Param("fieldNo")String fieldNo, @Param("userId")String userId);

	public List<VoFieldData> getFieldHistoryDatas(FieldHistoryCriteria criteria);

}
