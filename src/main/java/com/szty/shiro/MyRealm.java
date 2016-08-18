package com.szty.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.szty.bean.UserInfo;
import com.szty.bean.my.VoUserInfo;
import com.szty.enums.UserStatus;
import com.szty.service.UserService;

public class MyRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		VoUserInfo currentInfo =  (VoUserInfo) principals.getPrimaryPrincipal();
		
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		
		simpleAuthorizationInfo.addRole(currentInfo.getRole().name());

		return simpleAuthorizationInfo;
	}

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
        String principal = (String) token.getPrincipal();
        UserInfo systemUser = null;
        
		try {
			systemUser = userService.selectUser(principal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        if(systemUser == null){
            return null;
        } else if (systemUser.getStatus() == UserStatus.Lock){
        	return null;
        } else if (systemUser.getStatus() == UserStatus.Forbidden){
        	throw new DisabledAccountException();
        }
        
        String password = systemUser.getPassword();
        
        VoUserInfo userInfoVo = new VoUserInfo();
        
        userInfoVo.setUserId(systemUser.getUserId());
        userInfoVo.setUsername(systemUser.getUsername());
        userInfoVo.setTel(systemUser.getTel());
        userInfoVo.setRole(systemUser.getRole());
        userInfoVo.setBirthday(systemUser.getBirthday());
        userInfoVo.setHeadImage(systemUser.getHeadImage());
        
        //身份验证通过,返回一个身份信息
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfoVo, password, this.getName());
        
		return authenticationInfo;
	}
	
}
