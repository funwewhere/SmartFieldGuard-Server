package com.szty.controller;

import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.szty.bean.PostInfo;
import com.szty.bean.ReplyInfo;
import com.szty.bean.my.PostCriteria;

public class PostControllerTest {

	private ApplicationContext applicationContext;
	
	private PostController postController;
	
	@Before
	public void setUp() throws Exception {
		this.applicationContext = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","applicationContext-shiro.xml","spring-mvc.xml"});
		postController = (PostController) applicationContext.getBean("postController");
	}

	@Test
	public void testPublish() throws Exception{
		PostInfo postInfo = new PostInfo();
		postInfo.setUserId("44444");
		postInfo.setTitle("ee");
		postInfo.setContent("erere");
		postInfo.setType("01");
		try {
			int i =40;
			while(i-->0){
				postController.publish(postInfo, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Test
	public void testSolve() {
	}

	@Test
	public void testList() throws Exception {
		PostCriteria criteria = new PostCriteria();
		criteria.setPageIndex(2);
		criteria.setPageCount(8);
		postController.list(criteria);
	}

	@Test
	public void testReplyList() throws Exception {
		Map<String, Object> replyList = postController.replyList("PO001605270048", 1, 5);
		System.out.println(JSONObject.fromObject(replyList));
	}

	@Test
	public void testReply() throws Exception {
		ReplyInfo replyInfo = new ReplyInfo();
		replyInfo.setContent("机构中的危机主要分四大块：1、经营上的危机　2、使用者的危机3、服务人员的危机4、现场中发生的危机。经验告诉我们现场的发生机率最高，最不可控，以下将着重于现场中发生的危机处理做经验论述。");
		replyInfo.setPostNo("PO001605270058");
		replyInfo.setUserId("44444");
		postController.reply(replyInfo, null);
	}

	@Test
	public void testMyPostList() throws Throwable {
		
	}

}
