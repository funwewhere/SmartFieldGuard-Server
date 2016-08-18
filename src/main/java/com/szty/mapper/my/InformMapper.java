package com.szty.mapper.my;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.szty.bean.WaitingForInform;
import com.szty.enums.InformType;

public interface InformMapper {
	
	public List<WaitingForInform> getUserInforms(@Param("userId")String userId, @Param("type")InformType type);

	public int deleteOlderInform(@Param("userId")String userId, @Param("type")InformType type, @Param("createDate")Date date);
	
}
