package com.szty.service;

import java.util.List;

import com.szty.bean.my.VoCropInfoSimple;
import com.szty.bean.my.VoCropType;

public interface CropService {

	public List<VoCropType> getSubTypes(String cropTypeNo);

	public List<VoCropInfoSimple> getCropListSimple(String cropTypeNo);

}
