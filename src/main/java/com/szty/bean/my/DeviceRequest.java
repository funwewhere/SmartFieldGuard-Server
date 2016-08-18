package com.szty.bean.my;

import java.util.Date;

public class DeviceRequest {
	private String mType;
	
	private String userId;
	
	private String mac;
	
	private double soilT;
	
	private double soilW;
	
	private double airT;
	
	private double airW;
	
	private double light;

	private double co2;
	
	private Date nowDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public double getSoilT() {
		return soilT;
	}

	public void setSoilT(double soilT) {
		this.soilT = soilT;
	}

	public double getSoilW() {
		return soilW;
	}

	public void setSoilW(double soilW) {
		this.soilW = soilW;
	}

	public double getAirT() {
		return airT;
	}

	public void setAirT(double airT) {
		this.airT = airT;
	}

	public double getAirW() {
		return airW;
	}

	public void setAirW(double airW) {
		this.airW = airW;
	}

	public double getLight() {
		return light;
	}

	public void setLight(double light) {
		this.light = light;
	}

	public double getCo2() {
		return co2;
	}

	public void setCo2(double co2) {
		this.co2 = co2;
	}

	public Date getNowDate() {
		return nowDate;
	}

	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}

	public String getmType() {
		return mType;
	}

	public void setmType(String mType) {
		this.mType = mType;
	}

}
