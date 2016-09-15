package com.szty.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szty.bean.FieldTaskRecord;
import com.szty.bean.my.CropPlan;
import com.szty.bean.my.FieldPlanRecord;
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
	
	@RequiresAuthentication
	@RequestMapping(value="/fieldRecord",produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, Object> fieldRecord(String fieldNo, Integer pageIndex, Integer pageCount) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<FieldPlanRecord> fieldRecord = taskService.getFieldRecord(fieldNo, 1, 200);
		map.put("taskRecords", fieldRecord);
		return map;
	}
	
}