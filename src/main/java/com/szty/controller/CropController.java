package com.szty.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szty.bean.my.VoCropInfoSimple;
import com.szty.bean.my.VoCropType;
import com.szty.service.CropService;

@Controller
@RequestMapping("/crop")
public class CropController {
	
	@Autowired
	private CropService cropService;
	
	@RequestMapping(value="/types",produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, Object> types(String cropTypeNo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<VoCropType> cropTypes = cropService.getSubTypes(cropTypeNo);
		map.put("cropTypes", cropTypes);
		return map;
	}
	
	@RequestMapping(value="/list",produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, Object> crops(String cropTypeNo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<VoCropInfoSimple> cropList = cropService.getCropListSimple(cropTypeNo);
		map.put("cropList", cropList);
		return map;
	}
	
}