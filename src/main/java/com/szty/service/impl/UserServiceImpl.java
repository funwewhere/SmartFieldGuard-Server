package com.szty.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.szty.bean.UserInfo;
import com.szty.enums.FileTpye;
import com.szty.enums.Table;
import com.szty.enums.UserRole;
import com.szty.enums.UserStatus;
import com.szty.exception.CustomException;
import com.szty.mapper.UserInfoMapper;
import com.szty.mapper.my.UserMapper;
import com.szty.service.UserService;
import com.szty.util.EncryptUtil;
import com.szty.util.FileUtil;
import com.szty.util.PageUtil;
import com.szty.util.SystemUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private SystemUtil keyGenerator;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Override
	public UserInfo selectUser(String principal) throws Exception {
		List<UserInfo> users = userMapper.selectUserInfoByTel(principal);
		if(users.size() > 0){
			if(users.size() == 1){
				return users.get(0);
			}
			throw new Exception("存在多个标识为：" + principal + " 的用户");
		}
		
		users = userMapper.selectUserInfoByUsername(principal);
		if(users.size() > 0){
			if(users.size() == 1){
				return users.get(0);
			}
			throw new Exception("存在多个标识为：" + principal + " 的用户");
		}
		
		return null;
	}

	@Override
	public void validateRegisterInfo(UserInfo userInfo)
			throws Exception {
		String Tel = userInfo.getTel();
		if(Tel == null || Tel.isEmpty()) {
			throw new CustomException("手机号码不能为空");
		}
		if (!userInfo.getTel().matches("^((13[0-9])|(15[^4,\\D])|(18[0,0-9])|(17[0,0-9]))\\d{8}$")){
			throw new CustomException("手机号码格式错误");
		}
	}

	@Override
	public String saveRegisterInfo(UserInfo userInfo) throws Exception {
		UserInfo user = this.selectUser(userInfo.getTel());

		if( user != null && !(user.getStatus() == UserStatus.Lock)){
			throw new CustomException("该手机号已被注册");
		}
		
		String key = null;
		
		userInfo.setUsername(userInfo.getTel());
		userInfo.setStatus(UserStatus.Lock);
		userInfo.setRole(UserRole.NormalMember);
		
		if(user == null){
			key = keyGenerator.gerneratorKey(Table.USER);
			
			userInfo.setUserId(key);
			userInfoMapper.insert(userInfo);
		} else {
			key = user.getUserId();
			
			userInfo.setUserId(key);
			userInfoMapper.updateByPrimaryKey(userInfo);
		}
		
		return key;
	}
	
	@Override
	public void unlockUser(String userId) throws Exception {
		UserInfo user = userInfoMapper.selectByPrimaryKey(userId);
		if(user == null || user.getStatus() == UserStatus.Lock){
			throw new Exception("错误用户编号");
		}
		user.setStatus(UserStatus.Normal);
		user.setRegisterDate(new Date());
		userInfoMapper.updateByPrimaryKey(user);
	}

	@Override
	public String register(UserInfo userInfo) throws Exception {
		String tel = userInfo.getTel();
		if(tel == null || tel.isEmpty()) {
			throw new CustomException("手机号码不能为空");
		} else if (!userInfo.getTel().matches("^((13[0-9])|(15[^4,\\D])|(18[0,0-9])|(17[0,0-9]))\\d{8}$")){
			throw new CustomException("手机号码格式错误");
		}
		
		String username = userInfo.getUsername();
		if (username ==null || username.isEmpty()) {
			throw new CustomException("用户名不能为空");
		}
		
		String password = userInfo.getPassword();
		if(password == null || password.isEmpty()) {
			throw new CustomException("密码不能为空");
		}
		
		UserInfo user = this.selectUser(tel);
		if( user != null && !(user.getStatus() == UserStatus.Lock)){
			throw new CustomException("该手机号已被注册");
		}
		
		user = this.selectUser(username);
		if( user != null && !(user.getStatus() == UserStatus.Lock)){
			throw new CustomException("该用户名已被注册");
		}
		
		//TODO
		if (StringUtils.isBlank(userInfo.getHeadImage())) {
			userInfo.setHeadImage("/SZTY/UserHead/666.jpg");
		}
		
		String key = null;
		userInfo.setPassword(EncryptUtil.encrypt(password));
		userInfo.setStatus(UserStatus.Normal);
		if (userInfo.getRole() == null || userInfo.getRole()==UserRole.Admin){
			userInfo.setRole(UserRole.NormalMember);
		}
		Date nowDate = new Date();
		userInfo.setRegisterDate(nowDate);
		userInfo.setLastActiveDate(nowDate);
		
		if(user == null){
			key = keyGenerator.gerneratorKey(Table.USER);
			userInfo.setUserId(key);
			userInfoMapper.insert(userInfo);
		} else {
			key = user.getUserId();
			userInfo.setUserId(key);
			userInfoMapper.updateByPrimaryKey(userInfo);
		}
		
		return key;
	}

	@Override
	public PageUtil<UserInfo> getUserList(int pageIndex, int pageCount)
			throws Exception {
		int count = userMapper.selectUserCount();
		List<UserInfo> users = new ArrayList<UserInfo>();
		PageUtil<UserInfo> page = new PageUtil<UserInfo>(pageIndex, count, pageCount);
		if (count >0 ) {
			users.addAll(userMapper.selectUserInfo(page.getDataStart(), pageCount));
		}
		page.setList(users);
		return page;
	}

	@Override
	public void lock(String userId) throws Exception {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
		UserStatus status = userInfo.getStatus();
		if (status == UserStatus.Lock) {
			userInfo.setStatus(UserStatus.Normal);
		} else if (status == UserStatus.Normal) {
			userInfo.setStatus(UserStatus.Lock);
		} else {
			return;
		}
		userInfoMapper.updateByPrimaryKey(userInfo);
	}

	@Override
	public UserInfo getUser(String userId) throws Exception {
		return userInfoMapper.selectByPrimaryKey(userId);
	}

	@Override
	public String uploadFile(FileTpye fileType, MultipartFile[] files, String userId) throws Exception {
		if (files == null || files.length == 0) {
			return null;
		}
		
		String savePath = fileUtil.saveFileAndMini(fileType, files[0], files[1], userId);
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
		userInfo.setHeadImage(savePath);
		userInfoMapper.updateByPrimaryKeySelective(userInfo);
		
		return savePath;
	}

	
}
