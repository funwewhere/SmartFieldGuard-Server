package com.szty.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.szty.bean.UserInfo;
import com.szty.bean.my.VoUserInfo;
import com.szty.enums.FileTpye;
import com.szty.exception.CustomException;
import com.szty.service.UserService;
import com.szty.util.PageUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	
//	private Logger log = Logger.getLogger(UserController.class);
	
	@Value("${staticUrlPrefix}")
	private static String staticUrlPrefix;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/register",produces="application/json;charset=utf-8")
	public @ResponseBody Map<String,Object> register(UserInfo userInfo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		String userId = userService.register(userInfo);
		map.put("userId", userId);
		return map;
	}
	
	@RequestMapping("/login")
	public @ResponseBody Map<String,Object> login(HttpServletRequest request, HttpServletResponse response) throws CustomException{
		Map<String, Object> map = new HashMap<String, Object>();
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		if(exceptionClassName!=null){
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				throw new CustomException("用户名/密码错误");
			} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
				throw new CustomException("用户名/密码错误");
			} else if(DisabledAccountException.class.getName().equals(exceptionClassName)){
				throw new CustomException("该账户被禁用 ");
			}else {
				throw new CustomException("验证失败");
			}
		} else {
			Subject subject = SecurityUtils.getSubject();
			if (subject.isAuthenticated()){
				VoUserInfo currentUser = (VoUserInfo) subject.getPrincipal();
				request.getSession().setAttribute("currentUser", currentUser);
				System.out.println("已登录");
				map.put("userInfo", currentUser);
				map.put("staticUrlPrefix", staticUrlPrefix);
			} else {
				throw new CustomException("登陆错误");
			}
		}
		return map;
	}
	
	@RequestMapping("/loginSuccess")
	public @ResponseBody Map<String,Object> loginSuccess(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		VoUserInfo currentUser = (VoUserInfo) SecurityUtils.getSubject().getPrincipal();
		request.getSession().setAttribute("currentUser", currentUser);
		map.put("userInfo", currentUser);
		map.put("staticUrlPrefix", staticUrlPrefix);
		return map;
	}
	
	@RequestMapping(value="/upload",produces="application/json;charset=utf-8")
	public @ResponseBody Map<String,Object> upload(FileTpye fileType, @RequestParam("file") MultipartFile[] files, HttpServletRequest request) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		VoUserInfo currentUser = (VoUserInfo) request.getSession().getAttribute("currentUser");
		userService.uploadFile(fileType, files, currentUser.getUserId());
		return map;
	}
	
	@RequiresRoles("VIPMember")
	@RequestMapping(value="/list",produces="application/json;charset=utf-8")
	public @ResponseBody Map<String,Object> getList(int pageIndex, int pageCount, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		PageUtil<UserInfo> page = userService.getUserList(pageIndex, pageCount);
		map.put("page", page);
		return map;
	}
	
	@RequestMapping(value="/judgeLogin",produces="application/json;charset=utf-8")
	public @ResponseBody Map<String,Object> judgeLogin(String userId, HttpServletRequest request, HttpServletResponse response) throws Exception{
//		socketService.sendMessage("rrr", "WO LAI le !!!!!!!!!!");
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession(false);
		if (session == null ||session.getAttribute("currentUser") == null) {
			throw new CustomException("unlogin");
		}
		return map;
	}
	
	@RequestMapping(value="/lock",produces="application/json;charset=utf-8")
	public @ResponseBody Map<String,Object> lockUser(String userId, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		userService.lock(userId);
		return map;
	}

}
