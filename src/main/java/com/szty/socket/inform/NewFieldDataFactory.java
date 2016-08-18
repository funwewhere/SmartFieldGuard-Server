package com.szty.socket.inform;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.szty.bean.WaitingForInform;
import com.szty.bean.my.DeviceRequest;
import com.szty.bean.my.VoFieldData;
import com.szty.enums.InformType;

@Component
public class NewFieldDataFactory extends AbstractInformFactory {

	private DeviceRequest deviceData;

	public NewFieldDataFactory() {
		super(InformType.NewFieldData, false);
	}

	public void init(DeviceRequest deviceData) {
		this.data = null;
		this.informList = null;
		this.informUserIds = null;
		this.deviceData = deviceData;
	}

	@Override
	public Map<String, Object> createData(List<WaitingForInform> informList) {
		if(data == null){
			data = new HashMap<String, Object>();
			VoFieldData fieldData = new VoFieldData(deviceData);
			data.put("fieldData", fieldData);
		}
		return data;
	}

	@Override
	public List<Inform> createInforms() {
		return informList;
	}

	@Override
	public Set<String> getInformUserIds() {
		if(informUserIds == null){
			informUserIds = new HashSet<String>();
			informUserIds.add(deviceData.getUserId());
		}
		return informUserIds;
	}


}
