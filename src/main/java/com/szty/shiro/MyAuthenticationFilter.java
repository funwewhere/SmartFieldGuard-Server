package com.szty.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;

public class MyAuthenticationFilter extends AuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		String username= request.getParameter("username");
		String password= request.getParameter("password");
		System.out.println("username2:"+username);
		System.out.println("password2:"+password);
		AuthenticationToken token = new UsernamePasswordToken(username, password);
		try {
	        Subject subject = getSubject(request, response);
	        subject.login(token);
//	        WebUtils.issueRedirect(request, response, "/user/loginSuccess");
	        issueSuccessRedirect(request, response);
	        return false;
        } catch (AuthenticationException e) {
        	String className = e.getClass().getName();
            request.setAttribute("shiroLoginFailure", className);
            return true;
        }
	}

}
