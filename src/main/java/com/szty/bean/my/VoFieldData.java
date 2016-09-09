package com.szty.bean.my;

import java.util.Date;

public class VoFieldData {
	private Double soilT;

    private Double soilW;

    private Double airT;

    private Double airW;

    private Double co2;

    private Double light;
    
    private String deviceMac;
    
    private Date recordTime;
    
    private int num;
    
    private long totalTime;
    
    {
    	soilT = 0d;
    	soilW = 0d;
    	airT = 0d;
    	airW = 0d;
    	co2 = 0d;
    	light = 0d;
    	totalTime = 0l;
    	num = 0;
    }
    
    public VoFieldData() {}
    
    public VoFieldData(DeviceRequest deviceData) {
		airT = deviceData.getAirT();
		airW = deviceData.getAirW();
		soilT = deviceData.getSoilT();
		soilW = deviceData.getSoilW();
		light = deviceData.getLight();
		co2 = deviceData.getCo2();
		recordTime = deviceData.getNowDate();
	}
    
    public void addFieldData(VoFieldData fieldData){
    	soilT += fieldData.soilT;
    	soilW += fieldData.soilW;
    	airT += fieldData.airT;
    	airW += fieldData.airW;
    	co2 += fieldData.co2;
    	light += fieldData.light;
    	totalTime += fieldData.getRecordTime().getTime();
    	num++;
    }
    
	public boolean isEmpty() {
		return num == 0;
	}
    
    public void calculateAverageValue(){
    	if (num == -1) return;
    	soilT = soilT/num;
    	soilW = soilW/num;
    	airT/=num;
    	airW/=num;
    	co2/=num;
    	light/=num;
    	totalTime/=num;
    	num = -1;
    	this.recordTime = new Date(totalTime);
    }
    
	public Double getSoilT() {
		return soilT;
	}

	public void setSoilT(Double soilT) {
		this.soilT = soilT;
	}

	public Double getSoilW() {
		return soilW;
	}

	public void setSoilW(Double soilW) {
		this.soilW = soilW;
	}

	public Double getAirT() {
		return airT;
	}

	public void setAirT(Double airT) {
		this.airT = airT;
	}

	public Double getAirW() {
		return airW;
	}

	public void setAirW(Double airW) {
		this.airW = airW;
	}

	public Double getCo2() {
		return co2;
	}

	public void setCo2(Double co2) {
		this.co2 = co2;
	}

	public Double getLight() {
		return light;
	}

	public void setLight(Double light) {
		this.light = light;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

}
