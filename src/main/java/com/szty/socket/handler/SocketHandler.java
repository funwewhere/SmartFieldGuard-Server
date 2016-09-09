package com.szty.socket.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szty.bean.FieldInfo;
import com.szty.bean.my.DeviceRequest;
import com.szty.bean.my.MobileRequest;
import com.szty.bean.my.MobileResponse;
import com.szty.converter.JsonDate2LongProcessor;
import com.szty.enums.DeviceRequestType;
import com.szty.enums.MobileRequestType;
import com.szty.service.FieldService;
import com.szty.service.PushInformService;
import com.szty.service.UserService;
import com.szty.socket.inform.NewFieldDataFactory;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Service
public class SocketHandler {
	
	private static final Logger log = Logger.getLogger(SocketHandler.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FieldService fieldService;
	
	@Autowired
	private PushInformService pushInformService;
	
	@Autowired
	private NewFieldDataFactory newFieldDataFactory;
	
	private Map<String, IoSession> socketSessionMap;
	
	private JsonConfig jsonConfig;
	
	{
		this.socketSessionMap = new HashMap<String, IoSession>();
		jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDate2LongProcessor());
	}
	
	public void processMessage(Object message, IoSession session) {
		if (message instanceof DeviceRequest) {
			DeviceRequest deviceData = (DeviceRequest) message;
			if (processDeviceData(deviceData, session)){
				String key = "device_" + deviceData.getMac();
				if(!isUseful(key, session)){
					session.setAttribute("key", key);
					socketSessionMap.put(key, session);
				}
			}
		} else if (message instanceof MobileRequest) {
			MobileRequest mobileData = (MobileRequest) message;
			if (processMobileData(mobileData, session)){
				String key = "user_" + mobileData.getSendUser();
				if(!isUseful(key, session)){
					session.setAttribute("key", key);
					socketSessionMap.put(key, session);
				}
			}
		}
	}
	
	/**
	 * 处理移动端信息
	 * @param mobileData
	 * @param session
	 * @return
	 */
	private boolean processMobileData(MobileRequest mobileData, IoSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "success");
		try {
			String userId = mobileData.getSendUser();
			if(StringUtils.isBlank(userId)){
				return false;
			}
			MobileRequestType requestType = MobileRequestType.valueOf(mobileData.getmType());
			switch (requestType) {
				case NewConnect:
					if(userService.getUser(userId)==null){
						log.info("没有对应用户");
						return false;
					}
					String key = "user_" + userId;
					session.setAttribute("key", key);
					socketSessionMap.put(key, session);
					pushInformService.pushAllInformToUsers(userId);
					return false;
				case KeepLive:
					break;
				case GetInform:
					pushInformService.clearSuccessInform(userId, mobileData.getInformKey());
					break;
				case upDateFieldData:
					fieldService.getDeviceNewestData(userId, mobileData.getFieldNo());
					break;
				default:
					log.info("无效请求类型");
					return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", "error");
			return false;
		}
		sendMessage(session, map);
		return true;
	}

	/**
	 * 处理设备信息
	 * @param deviceData
	 * @param session
	 * @return
	 */
	private boolean processDeviceData(DeviceRequest deviceData, IoSession session) {
		try {
	        DeviceRequestType requestType = DeviceRequestType.valueOf(deviceData.getmType());
	        switch (requestType) {
				case Data:
					if(fieldService.updateFieldData(deviceData)){
						newFieldDataFactory.init(deviceData);
						pushInformService.pushToUsers(newFieldDataFactory);
					}
					break;
				case New:
					FieldInfo fieldInfo = new FieldInfo();
					fieldInfo.setUserId(deviceData.getUserId());
					fieldInfo.setDeviceMac(deviceData.getMac());
					fieldService.addField(fieldInfo);
					break;
				default:
					session.write("error\n");
					return false;
	        }
		} catch (Exception e) {
			e.printStackTrace();
			session.write("error\n");
			return false;
		}
		session.write("success\n");
		return true;
	}
	
	public boolean sendMessage(String deviceId, Object message) {
		if(!socketSessionMap.containsKey(deviceId)){
			System.out.println(deviceId + "不在~~");
			return false;
		}
		IoSession ioSession = socketSessionMap.get(deviceId);
		if(!ioSession.isConnected()){
			System.out.println(deviceId + "断线~~");
			return false;
		}
		sendMessage(ioSession, message);
		return true;
	}
	
	private void sendMessage(IoSession ioSession, Object message){
		if(message instanceof String){
			ioSession.write(message);
		} else {
			MobileResponse response = new MobileResponse();
			String data = JSONObject.fromObject(message, jsonConfig).toString();
			response.setData(data);
			ioSession.write(response);
		}
	}
	
	public IoSession removeSession(String sessionName) {
		log.info("removeSession " + sessionName);
		return socketSessionMap.remove(sessionName);
	}
	
	public IoSession removeSession(IoSession session) {
		if(socketSessionMap.containsValue(session)){
			String key = (String)session.getAttribute("key");
			return socketSessionMap.remove(key);
		}
		return null;
	}
	
	/**
	 * 判断session是否存在有效
	 * @param key
	 * @param session
	 * @return
	 */
	private boolean isUseful(String key, IoSession session){
		if(socketSessionMap.containsKey(key)){
			IoSession ioSession = socketSessionMap.get(key);
			if (session == ioSession) {
				return true;
			}
		}
		return false;
	}
	
}
