package com.szty.mapper.my;

import java.util.List;

import com.szty.bean.my.VoFieldRecord;

public interface TaskMapper {

	public List<VoFieldRecord> getFieldRecords(String fieldNo);
	
}
