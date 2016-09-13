package com.szty.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szty.bean.FieldTaskRecord;
import com.szty.bean.my.CropPlan;
import com.szty.service.TaskService;

@Controller
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@RequestMapping(value="/download",produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, Object> download(String cropNo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		CropPlan cropPlan = taskService.download(cropNo);
		map.put("cropPlan", cropPlan);
		return map;
	}
	
	@RequiresAuthentication
	@RequestMapping(value="/finish",produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, Object> finish(FieldTaskRecord fieldTaskRecord) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		taskService.recordFieldTask(fieldTaskRecord);
		return map;
	}
	
}