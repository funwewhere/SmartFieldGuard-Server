package com.szty.aop;

import java.util.HashMap;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.szty.exception.CustomException;

@Aspect
@Component
public class MainAspect {
	
	public Logger log = Logger.getLogger(MainAspect.class);

	@Pointcut("execution(* com.szty.controller.*.*(..))")
	public void newRequest(){};
	
	@SuppressWarnings("unchecked")
	@Around("newRequest()")
	public Object before(ProceedingJoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();
		log.info("【enter】 ---> [" +  joinPoint.getSignature() + "]");
		for(Object arg : args){
			if(arg instanceof ServletRequest || arg instanceof ServletResponse) continue;
			if(arg instanceof String || arg instanceof Integer || arg instanceof Double || arg instanceof Float || arg instanceof Character || arg instanceof Boolean) {
				log.info("param:[" + arg.getClass().getName() + "]" + "=" + arg);
				continue;
			}
			if(arg == null){
				log.info("param:[ NULL ]");
				continue;
			}
			try{
				log.info("param:[" + arg.getClass().getName() + "] = " + JSONObject.fromObject(arg).toString());
			} catch (Exception e) {
				log.info("param:[" + arg.getClass().getName() + "] parse wrong");
			}
		}
		Object result = null;
		try {
			result = joinPoint.proceed();
			if(result instanceof HashMap){
				((HashMap<String, Object>)result).put("message", "success");
			}
		} catch (CustomException e) {
			result = new HashMap<String, Object>();
			((HashMap<String, Object>)result).put("message", e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			result = new HashMap<String, Object>();
			((HashMap<String, Object>)result).put("message", "system error");
		}
		//log.info("【result】=" + JSONObject.fromObject(result));
		//log.info("【exit】 <--- [" +  joinPoint.getSignature() + "]");
		return result;
	}
}
