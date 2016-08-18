package com.szty.mapper.my;

import org.apache.ibatis.annotations.Param;

public interface SystemMapper {
	public String getAndLockTableKey(String tableName);
	
	public int updateTableKey(@Param("tableName")String tableName, @Param("key")String key);
}
