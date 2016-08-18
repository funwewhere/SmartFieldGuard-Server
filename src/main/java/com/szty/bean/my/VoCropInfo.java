package com.szty.bean.my;

import java.util.Map;


public class VoCropInfo extends VoCropInfoSimple {
	private String intro;

    private String temperature;

    private String light;

    private String water;

    private String soil;
    
    private Integer growDays;
    
    private Map<String, String> cropVariableList;

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getLight() {
		return light;
	}

	public void setLight(String light) {
		this.light = light;
	}

	public String getWater() {
		return water;
	}

	public void setWater(String water) {
		this.water = water;
	}

	public String getSoil() {
		return soil;
	}

	public void setSoil(String soil) {
		this.soil = soil;
	}

	public Map<String, String> getCropVariableList() {
		return cropVariableList;
	}

	public void setCropVariableList(Map<String, String> cropVariableList) {
		this.cropVariableList = cropVariableList;
	}

	public Integer getGrowDays() {
		return growDays;
	}

	public void setGrowDays(Integer growDays) {
		this.growDays = growDays;
	}
    
}