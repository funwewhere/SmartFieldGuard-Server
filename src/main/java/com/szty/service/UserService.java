package com.szty.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.szty.bean.UserInfo;
import com.szty.enums.FileTpye;
import com.szty.enums.UserRole;
import com.szty.util.PageUtil;

public interface UserService {
	public UserInfo selectUser(String principal) throws Exception;

	public void validateRegisterInfo(UserInfo userInfo) throws Exception;

	public String saveRegisterInfo(UserInfo userInfo) throws Exception;

	public void unlockUser(String userId) throws Exception;

	public String register(UserInfo userInfo) throws Exception;

	public List<UserInfo> getUserList(UserRole role, String cropNo) throws Exception;

	public void lock(String userId) throws Exception;

	public UserInfo getUser(String userId) throws Exception;

	public String uploadFile(FileTpye fileType, MultipartFile[] files, String userId) throws Exception;
}
