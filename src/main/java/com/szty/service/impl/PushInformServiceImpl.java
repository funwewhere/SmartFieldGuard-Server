package com.szty.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.szty.bean.WaitingForInform;
import com.szty.enums.InformType;
import com.szty.mapper.WaitingForInformMapper;
import com.szty.mapper.my.InformMapper;
import com.szty.service.PushInformService;
import com.szty.socket.handler.SocketHandler;
import com.szty.socket.inform.AbstractInformFactory;
import com.szty.socket.inform.Inform;
import com.szty.socket.inform.NewExpertAskFactory;
import com.szty.socket.inform.NewReplyFactory;

@Service
public class PushInformServiceImpl implements PushInformService {
	
	@Autowired
	private SocketHandler socketHandler;
	
	@Autowired
	private InformMapper informMapper;
	
	@Autowired
	private WaitingForInformMapper waitingForInformMapper;
	
	@Autowired
	private NewReplyFactory newReplyFactory;
	
	@Autowired
	private NewExpertAskFactory newExpertAskFactory;
	
	private HashMap<String, Inform> informMap;
	
	private Timer timer;
	
	private static Logger log = Logger.getLogger(PushInformServiceImpl.class);
	
	{
		informMap = new HashMap<String, Inform>();
	}
	
	public PushInformServiceImpl() {
		//每30秒清除一次map里失败的推送
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					insertFailInfrom();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} , 30000, 30000);
		log.info("定时清除任务开始...");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void pushToUsers(AbstractInformFactory informFactory) throws Exception{
		Map<String, Object> data = informFactory.createData(null);
		if(informFactory.isForce()){
			List<Inform> informs = informFactory.createInforms();
			String key = "CA" + UUID.randomUUID().toString().replace("-", "");
			informMap.put(key , informs.get(0));
			data.put("key", key);
		}
		data.put("type", informFactory.getType());
		Set<String> userIds = informFactory.getInformUserIds();
		for (String userId : userIds) {
			socketHandler.sendMessage("user_" + userId, data);
		}
	}
	
	@Override
	public void insertFailInfrom() throws Exception{
		synchronized (informMap) {
			Set<Entry<String, Inform>> entrySet = informMap.entrySet();
			Iterator<Entry<String, Inform>> iterator = entrySet.iterator();
			long nowDate = System.currentTimeMillis();
			while(iterator.hasNext()){
				Entry<String, Inform> next = iterator.next();
				WaitingForInform inform = next.getValue().getWaitingForInform();
				Date createDate = inform.getCreateDate();
				//推送超过15秒没有回应则存入数据库，并删除map中的推送
				if(nowDate - createDate.getTime() > 15000l){
					Set<String> userIds = next.getValue().getUserIds();
					for (String userId : userIds) {
						inform.setInformUser(userId);
						waitingForInformMapper.insert(inform);
					}
					iterator.remove();
				}
			}
		}
	}

	@Override
	public void clearSuccessInform(String sendUser, String informKey) throws Exception {
		//判断通知是在map还是在数据库中
		if (informKey.startsWith("CA")) {
			Inform inform = informMap.get(informKey);
			synchronized (inform){
				Set<String> userIds = inform.getUserIds();
				Iterator<String> iterator = userIds.iterator();
				while(iterator.hasNext()){
					if(iterator.next().equals(sendUser)){
						iterator.remove();
						break;
					}
				}
				if(userIds.isEmpty()){
					informMap.remove(informKey);
				}
			}
		} else {
			Date date = new Date(Long.parseLong(informKey));
			informMapper.deleteOlderInform(sendUser, InformType.NewReply, date);
		}
	}

	@Override
	public void pushAllInformToUsers(String userId) throws Exception {
		//获取数据库中所有关于该用户未发出的通知
		List<WaitingForInform> userInforms = informMapper.getUserInforms(userId, InformType.NewReply);
		if (userInforms != null && !userInforms.isEmpty()) {
			Map<String, Object> data = newReplyFactory.createData(userInforms);
			//将第一条最大的时间戳设成key
			data.put("key", userInforms.get(0).getCreateDate().getTime());
			data.put("type", InformType.NewReply);
			socketHandler.sendMessage("user_"+userId, data);
		}
		//获取数据库中所有关于该用户未发出的通知
		userInforms = informMapper.getUserInforms(userId, InformType.NewExpertAsk);
		if (userInforms != null && !userInforms.isEmpty()) {
			Map<String, Object> data = newExpertAskFactory.createData(userInforms);
			//将第一条最大的时间戳设成key
			data.put("key", userInforms.get(0).getCreateDate().getTime());
			data.put("type", InformType.NewExpertAsk);
			socketHandler.sendMessage("user_"+userId, data);
		}
	}

}
