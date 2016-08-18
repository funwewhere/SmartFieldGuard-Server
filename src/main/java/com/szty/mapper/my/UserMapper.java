package com.szty.mapper.my;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.szty.bean.UserInfo;

public interface UserMapper {
	public List<UserInfo> selectUserInfoByUsername(String username) throws Exception;
	
	public List<UserInfo> selectUserInfoByTel(String tel) throws Exception;
	
	public void updateUserLock(@Param("status") String status, @Param("userId") String userId) throws Exception;

	public int selectUserCount() throws Exception;

	public List<UserInfo> selectUserInfo(@Param("start") int start, @Param("pageCount") int pageCount) throws Exception;
}
