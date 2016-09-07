package com.szty.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.szty.bean.PostInfo;
import com.szty.bean.ReplyInfo;
import com.szty.bean.UserInfo;
import com.szty.bean.my.PostCriteria;
import com.szty.bean.my.VoPost;
import com.szty.bean.my.VoPostSimple;
import com.szty.bean.my.VoReply;
import com.szty.enums.FileTpye;
import com.szty.enums.PostStatus;
import com.szty.enums.Table;
import com.szty.mapper.PostInfoMapper;
import com.szty.mapper.ReplyInfoMapper;
import com.szty.mapper.UserInfoMapper;
import com.szty.mapper.my.PostMapper;
import com.szty.mapper.my.ReplyMapper;
import com.szty.service.PostService;
import com.szty.util.FileUtil;
import com.szty.util.PageUtil;
import com.szty.util.SystemUtil;

@Service
public class PostServiceImpl implements PostService {
	
	private Logger log = Logger.getLogger(PostServiceImpl.class);
	
	@Autowired
	private SystemUtil systemUtil;

	@Autowired
	private PostInfoMapper postInfoMapper;

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private ReplyInfoMapper replyInfoMapper;
	
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Override
	public String publish(PostInfo postInfo, MultipartFile[] iamges) throws Exception {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(postInfo.getUserId());
		if (userInfo == null) {
			throw new Exception("user id error");
		}
		
//		String title = postInfo.getTitle();
//		if (title == null || StringUtils.isBlank(title)) {
//			throw new Exception("title is null");
//		}
		
		String content = postInfo.getContent();
		if (StringUtils.isBlank(content)) {
			throw new Exception("content is null");
		}
		
		StringBuffer strBuffer = new StringBuffer();
		int i = 0;
		for (int len = iamges==null ? 0 : iamges.length; i<len; ++i ) {
			strBuffer.append(fileUtil.saveFileAndMini(FileTpye.PostImage, iamges[i], iamges[++i], null));
			if (i < len -1) {
				strBuffer.append("|");
			}
		}
		
		if (i > 0) {
			postInfo.setImages(strBuffer.toString());
		}
		
		Date nowDate = new Date();
		String postNo = systemUtil.gerneratorKey(Table.POST);
		postInfo.setPostNo(postNo);
		postInfo.setStatus(PostStatus.Unsolved);
		postInfo.setReplyNum(0);
		postInfo.setBestReplyNo(null);
		postInfo.setCreateDate(nowDate);
		postInfo.setLastReplyDate(nowDate);
		postInfoMapper.insert(postInfo);
		
		strBuffer.setLength(0);
		strBuffer.append(postNo);
		strBuffer.append("|");
		strBuffer.append(nowDate.getTime());
		if (i > 0) {
			strBuffer.append("|");
			strBuffer.append(postInfo.getImages());
		}
		return strBuffer.toString();
	}

	@Override
	public void solve(String postNo, Integer replySn) throws Exception {
		PostInfo postInfo = postInfoMapper.selectByPrimaryKey(postNo);
		if (postInfo == null || PostStatus.Resolved == postInfo.getStatus()) {
			log.info("[postNo:" + postNo + "] post error");
			return;
		}
		
		ReplyInfo replyInfo = replyInfoMapper.selectByPrimaryKey(postNo, replySn);
		if (replyInfo == null) {
			log.info("[postNo:" + postNo + "][replySn:" + replySn + "] reply error");
			return;
		}
		int num = replyMapper.updateReplyToBest(postNo, replySn);
		if (num != 1) {
			throw new Exception("update replySn fail");
		}
		postInfo.setStatus(PostStatus.Resolved);
		postInfo.setBestReplyNo(replySn.toString());
		num = postInfoMapper.updateByPrimaryKey(postInfo);
		if (num != 1) {
			throw new Exception("update poststatus fail");
		}
	}

	@Override
	public PageUtil<VoPostSimple> getList(PostCriteria criteria) throws Exception {
		Integer pageIndex = criteria.getPageIndex();
		Integer pageCount = criteria.getPageCount();
		if (pageIndex == null || pageCount == null) {
			log.info("pageIndex、pageCount is null");
			return null;
		}
		
		int count = postMapper.getPostCount(criteria);
		PageUtil<VoPostSimple> page = new PageUtil<>(pageIndex, count, pageCount);
		if(count > 0 && pageIndex <= page.getPageSize()){
			criteria.setDataStart(page.getDataStart());
			List<VoPostSimple> postList = postMapper.getPostList(criteria);
			page.setList(postList);
		}
		return page;
	}

	@Override
	public VoPost getInfo(String postNo) throws Exception {
		VoPost post = postMapper.getPostInfo(postNo);
		return post;
	}

	@Override
	@Transactional
	public boolean reply(ReplyInfo replyInfo, MultipartFile[] iamges)
			throws Exception {
		String userId = replyInfo.getUserId();
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
		if (userInfo == null) {
			log.info("user id [" + userId + "] error");
			return false;
		}
		
		String postNo = replyInfo.getPostNo();
		Integer replyNum = postMapper.getAndLockPostReplyNum(postNo);
		if (replyNum == null) {
			log.info("post id [" + postNo + "] error");
			return false;
		}
		
		String content = replyInfo.getContent();
		if (userInfo == null || StringUtils.isBlank(content)) {
			throw new Exception("content is null");
		}
		
		StringBuffer imageUrls = new StringBuffer();
		int i = 0;
		for (int len = iamges==null?0:iamges.length; i<len; ++i ) {
			imageUrls.append(fileUtil.saveFileAndMini(FileTpye.PostImage, iamges[i], iamges[++i], null));
			if (i < len -1) imageUrls.append("|");
		}
		
		if (i > 0) {
			replyInfo.setImages(imageUrls.toString());
		}
		
		Date nowDate = new Date();
		replyInfo.setReplyDate(nowDate);
		replyInfo.setAgreeUsers("");
		replyInfo.setAgreeNum(0);
		replyInfo.setReplySn(++replyNum);
		replyInfoMapper.insert((replyInfo));
		
		PostInfo param = new PostInfo();
		param.setPostNo(postNo);
		param.setReplyNum(replyNum);
		param.setLastReplyDate(nowDate);
		int num = postInfoMapper.updateByPrimaryKeySelective(param);
		if (num != 1) {
			throw new Exception("update replyNum fail");
		}
		return true;
	}

	@Override
	public PageUtil<VoReply> getReplyList(String postNo, Integer lastReplySn, Integer pageIndex,
			Integer pageCount) {
		if (pageIndex == null || pageIndex < 1 || pageCount == null || pageCount < 1) {
			log.info("[pageIndex pageCount post error");
			return null;
		}
		
		PostInfo postInfo = postInfoMapper.selectByPrimaryKey(postNo);
		if (postInfo == null) {
			log.info("[postNo:" + postNo + "] post error");
			return null;
		}
		
		int count = postInfo.getReplyNum();
		PageUtil<VoReply> page = new PageUtil<>(pageIndex, count, pageCount);
		if (count > 0 && pageIndex <= page.getPageSize()) {
			List<VoReply> replyList = replyMapper.getReplyList(postNo, lastReplySn, page.getDataStart(), pageCount);
			page.setList(replyList);
		}
		return page;
	}

	@Override
	@Transactional
	public void replyAgree(String userId, String postNo, Integer replySn) throws Exception {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
		if (userInfo == null) {
			log.info("user id [" + userId + "] error");
			return;
		}
		
		ReplyInfo replyInfo = replyInfoMapper.selectByPrimaryKey(postNo, replySn);
		if (replyInfo == null) {
			log.info("postNo replySn [" + postNo + "][" + replySn + "] error");
			return;
		}
		
		String agreeUsers = replyMapper.getAndLockAgreeUsers(postNo, replySn);
		String[] users = StringUtils.split(agreeUsers, "|");
		int agreeNum = users.length;
		int len = agreeNum;
		StringBuffer stringBuffer = new StringBuffer();
		int i = 0;
		for (; i < len; ++i) {
			if(userId.equals(users[i])){
				agreeNum--;
				continue;
			}
			stringBuffer.append(users[i]);
			if (i < len -1) stringBuffer.append("|");
		}
		if (agreeNum == len) {
			if(agreeNum > 0)stringBuffer.append("|");
			stringBuffer.append(userId);
			agreeNum++;
		}
		
		replyInfo.setAgreeUsers(stringBuffer.toString());
		replyInfo.setAgreeNum(agreeNum);
		int num = replyInfoMapper.updateByPrimaryKeyWithBLOBs(replyInfo);
		if (num != 1) {
			throw new Exception("update agreeNum fail");
		}
	}

}
