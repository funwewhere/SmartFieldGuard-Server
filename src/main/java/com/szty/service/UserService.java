package com.szty.service;

import org.springframework.web.multipart.MultipartFile;

import com.szty.bean.UserInfo;
import com.szty.enums.FileTpye;
import com.szty.util.PageUtil;

public interface UserService {
	public UserInfo selectUser(String principal) throws Exception;

	public void validateRegisterInfo(UserInfo userInfo) throws Exception;

	public String saveRegisterInfo(UserInfo userInfo) throws Exception;

	public void unlockUser(String userId) throws Exception;

	public String register(UserInfo userInfo) throws Exception;

	public PageUtil<UserInfo> getUserList(int pageIndex, int pageCount) throws Exception;

	public void lock(String userId) throws Exception;

	public UserInfo getUser(String userId) throws Exception;

	public String uploadFile(FileTpye fileType, MultipartFile[] files, String userId) throws Exception;
}
