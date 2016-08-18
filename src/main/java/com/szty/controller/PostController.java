package com.szty.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.szty.bean.PostInfo;
import com.szty.bean.ReplyInfo;
import com.szty.bean.my.PostCriteria;
import com.szty.bean.my.VoPostSimple;
import com.szty.bean.my.VoReply;
import com.szty.bean.my.VoUserInfo;
import com.szty.service.PostService;
import com.szty.service.PushInformService;
import com.szty.socket.inform.NewReplyFactory;
import com.szty.util.PageUtil;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PushInformService pushInformService;
	
	@Autowired
	private NewReplyFactory newReplyFactory;
	
	@RequiresAuthentication
	@RequestMapping(value="/publish",produces="application/json;charset=utf-8", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> publish(PostInfo postInfo, @RequestParam("file") MultipartFile[] files,HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		VoUserInfo currentUser = (VoUserInfo) request.getSession().getAttribute("currentUser");
		postInfo.setUserId(currentUser.getUserId());
		String post = postService.publish(postInfo, files);
		map.put("postInfo", post);
		return map;
	}
	
	@RequiresAuthentication
	@RequestMapping(value="/solve",produces="application/json;charset=utf-8", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> solve(String postNo, Integer replySn) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		postService.solve(postNo, replySn);
		return map;
	}
	
	@RequestMapping(value="/list",produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, Object> list(PostCriteria criteria) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageUtil<VoPostSimple> postList = postService.getList(criteria);
		map.put("postList", postList);
		return map;
	}
	
	@RequestMapping(value="/replyList",produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, Object> replyList(String postNo, Integer lastReplySn,Integer pageIndex, Integer pageCount) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
//		if (pageIndex == 1) {
//			VoPost post = postService.getInfo(postNo);
//			map.put("post", post);
//		}
		PageUtil<VoReply> replyList = postService.getReplyList(postNo, lastReplySn, pageIndex, pageCount);
		map.put("replyList", replyList);
		return map;
	}
	
	@RequiresAuthentication
	@RequestMapping(value="/reply",produces="application/json;charset=utf-8", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> reply(ReplyInfo replyInfo/*, @RequestParam("file") MultipartFile[] iamges*/, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		VoUserInfo currentUser = (VoUserInfo) request.getSession().getAttribute("currentUser");
		replyInfo.setUserId(currentUser.getUserId());
		if(postService.reply(replyInfo, null)){
			newReplyFactory.init(replyInfo);
			pushInformService.pushToUsers(newReplyFactory);
			map.put("replyInfo", replyInfo.getPostNo() + "|" + replyInfo.getReplySn() + "|" + replyInfo.getReplyDate().getTime());
		}
		return map;
	}
	
	@RequiresAuthentication
	@RequestMapping(value="/agree",produces="application/json;charset=utf-8", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> agree(String postNo, Integer replySn, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		VoUserInfo currentUser = (VoUserInfo) request.getSession().getAttribute("currentUser");
		postService.replyAgree(currentUser.getUserId(), postNo, replySn);
		return map;
	}
	
	@RequiresAuthentication
	@RequestMapping(value="/my",produces="application/json;charset=utf-8", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> myPostList(PostCriteria criteria, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		VoUserInfo currentUser = (VoUserInfo) request.getSession().getAttribute("currentUser");
		criteria.setUserId(currentUser.getUserId());
		PageUtil<VoPostSimple> postList = postService.getList(criteria);
		map.put("postList", postList);
		return map;
	}
	
}