package com.szty.test;

import java.util.Date;

import net.sf.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.szty.bean.UserInfo;
import com.szty.bean.my.CropPlan;
import com.szty.enums.UserRole;
import com.szty.enums.UserStatus;
import com.szty.mapper.UserInfoMapper;
import com.szty.service.TaskService;

public class test1 {

	private ApplicationContext applicationContext;
	
	@Before
	public void setUp() throws Exception {
//		this.applicationContext = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
	}
	
	@Test
	public void test() {
		UserInfoMapper userInfoMapper = (UserInfoMapper) applicationContext.getBean("userInfoMapper");
		UserInfo record = new UserInfo();
		record.setBirthday(new Date());
		record.setStatus(UserStatus.Forbidden);
		record.setUserId("55555");
		record.setPassword("6666");
		record.setTel("15862252535");
		record.setUsername("sss");
		record.setRegisterDate(new Date());
		record.setRole(UserRole.NormalMember);
		
		userInfoMapper.insert(record );
	}
	
	@Test
	public void test1() {
		UserInfoMapper userInfoMapper = (UserInfoMapper) applicationContext.getBean("userInfoMapper");
		UserInfo selectByPrimaryKey = userInfoMapper.selectByPrimaryKey("55555");
		System.out.println(JSONObject.fromObject(selectByPrimaryKey));
	}
	
	@Test
	public void testEnum() {
		TaskService cropMapper =  (TaskService) applicationContext.getBean("taskServiceImpl");
		CropPlan cropInfo = cropMapper.download("1");
		System.out.println(JSONObject.fromObject(cropInfo));
	}
	@Test
	public void testString(){
//		String a = "hfdh\nfyeyy";
//		int indexOf = a.indexOf("\n");
//		System.out.println(indexOf);
//		if(indexOf!=-1){
//			System.out.println(StringUtils.substring(a, 0, indexOf) + "|" + StringUtils.substring(a, indexOf+1, a.length()));
//		}
		char[] buffer = new char[200];
		buffer[0] = '2';
		buffer[1] = '3';
		buffer[2] = '4';
		buffer[3] = '5';
		buffer[4] = '6';
		System.out.println(String.valueOf(buffer));
	}

}
