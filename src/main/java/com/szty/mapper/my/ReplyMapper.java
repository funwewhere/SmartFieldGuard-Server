package com.szty.mapper.my;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.szty.bean.my.VoReply;

public interface ReplyMapper {

	public List<VoReply> getReplyList(@Param("postNo")String postNo, @Param("lastReplySn")Integer lastReplySn, @Param("dataStart")Integer dataStart,
			@Param("pageCount")Integer pageCount);

	public String getAndLockAgreeUsers(@Param("postNo")String postNo, @Param("replySn")Integer replySn);

	public int updateReplyToBest(@Param("postNo")String postNo, @Param("replySn")Integer replySn);

	public List<String> getAllReplyUsers(String postNo);
	
	public VoReply getReplyInfo(@Param("postNo")String postNo, @Param("replySn")Integer replySn);
	
}
