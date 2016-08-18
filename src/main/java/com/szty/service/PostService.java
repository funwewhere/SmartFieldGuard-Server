package com.szty.service;

import org.springframework.web.multipart.MultipartFile;

import com.szty.bean.PostInfo;
import com.szty.bean.ReplyInfo;
import com.szty.bean.my.PostCriteria;
import com.szty.bean.my.VoPost;
import com.szty.bean.my.VoPostSimple;
import com.szty.bean.my.VoReply;
import com.szty.util.PageUtil;

public interface PostService {

	public String publish(PostInfo postInfo, MultipartFile[] iamges) throws Exception;

	public void solve(String postNo, Integer replySn) throws Exception;

	public PageUtil<VoPostSimple> getList(PostCriteria criteria) throws Exception;

	public VoPost getInfo(String postNo) throws Exception;

	public boolean reply(ReplyInfo replyInfo, MultipartFile[] iamges) throws Exception;

	public PageUtil<VoReply> getReplyList(String postNo, Integer lastReplySn, Integer pageIndex,
			Integer pageCount);

	public void replyAgree(String userId, String postNo, Integer replySn) throws Exception;

}
