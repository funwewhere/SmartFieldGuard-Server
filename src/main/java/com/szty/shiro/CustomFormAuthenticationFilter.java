package com.szty.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;


public class CustomFormAuthenticationFilter extends FormAuthenticationFilter{
//	@Autowired
//	private LoginMapper loginMapper;

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
//		HttpServletRequest request2 = (HttpServletRequest)request;
		
		
//		String method = request2.getMethod();
		String username= request.getParameter("username");
		String password= request.getParameter("password");
//		System.out.println("url:" + request2.getRequestURL());
//		System.out.println("method:" + method);
		System.out.println("username:"+username);
		System.out.println("password:"+password);
	
		return super.onAccessDenied(request, response);
	}

	
}