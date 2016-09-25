package com.szty.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szty.bean.FieldInfo;
import com.szty.bean.my.FieldHistoryCriteria;
import com.szty.bean.my.VoFeild;
import com.szty.bean.my.VoFieldData;
import com.szty.bean.my.VoUserInfo;
import com.szty.service.FieldService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/field")
public class FieldController {
	
	@Autowired
	private FieldService fieldService;
	
	@RequiresAuthentication
	@RequestMapping(value="/list",produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, Object> list(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		VoUserInfo currentUser = (VoUserInfo) request.getSession().getAttribute("currentUser");
		List<VoFeild> fields = fieldService.userFieldList(currentUser.getUserId());
		map.put("fields", fields);
		return map;
	}
	
	@RequiresAuthentication
	@RequestMapping(value="/set",produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, Object> set(FieldInfo fieldInfo, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		VoUserInfo currentUser = (VoUserInfo) request.getSession().getAttribute("currentUser");
		fieldInfo.setUserId(currentUser.getUserId());
		fieldService.setFieldInfo(fieldInfo);
		return map;
	}
	
	@RequiresAuthentication
	@RequestMapping(value="/data",produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, Object> data(String fieldNo, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		VoUserInfo currentUser = (VoUserInfo) request.getSession().getAttribute("currentUser");
		VoFieldData fieldData = fieldService.getNewestData(fieldNo, currentUser.getUserId());
		map.put("fieldData", fieldData);
		return map;
	}
	
	@RequiresAuthentication
	@RequestMapping(value="/analyze",produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, Object> history(FieldHistoryCriteria criteria, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		VoUserInfo currentUser = (VoUserInfo) request.getSession().getAttribute("currentUser");
		criteria.setUserId(currentUser.getUserId());
		List<VoFieldData> fieldDatas = fieldService.analyzeHistoryData(criteria);
		map.put("fieldDatas", fieldDatas);
		return map;
	}
	
	@RequiresAuthentication
	@RequestMapping(value="/cropRecord",produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, Object> historyCrop(String fieldNo, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = JSONObject.fromObject("{\"sun\":2353,\"water\":7524,\"T\":24,\"niaoSu\":95,\"puGai\":157,\"liuSuanJia\":103}");
		map.put("cropRecord", jsonObject);
		return map;
	}
	
}