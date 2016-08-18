package com.szty.socket.inform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szty.bean.ReplyInfo;
import com.szty.bean.WaitingForInform;
import com.szty.bean.my.VoReply;
import com.szty.enums.InformType;
import com.szty.mapper.my.PostMapper;
import com.szty.mapper.my.ReplyMapper;

@Component
public class NewReplyFactory extends AbstractInformFactory{
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Autowired
	private PostMapper postMapper;
	
	private ReplyInfo replyInfo;
	
	public NewReplyFactory() {
		super(InformType.NewReply, true);
	}

	public void init(ReplyInfo replyInfo) {
		data = null;
		informList = null;
		informUserIds = null;
		this.replyInfo = replyInfo;
		createInforms();
	}

	@Override
	public Map<String, Object> createData(List<WaitingForInform> informs) {
		if(informs == null){
			informs = new ArrayList<WaitingForInform>();
			informs.add(createInforms().get(0).getWaitingForInform());
		}
		data = new HashMap<String, Object>();
		List<VoReply> replyList = new ArrayList<VoReply>();
		for(WaitingForInform inform : informs){
			VoReply reply = replyMapper.getReplyInfo(inform.getNo(), Integer.parseInt(inform.getSno()));
			replyList.add(reply);
		}
		data.put("replyList", replyList);
		return data;
	}

	@Override
	public List<Inform> createInforms() {
		if(informList != null){
			return informList;
		}
		WaitingForInform waitingForInform = new WaitingForInform();
		waitingForInform.setCreateDate(replyInfo.getReplyDate());
		waitingForInform.setNo(replyInfo.getPostNo());
		waitingForInform.setSno(Integer.toString(replyInfo.getReplySn()));
		waitingForInform.setType(type);
		
		Inform inform = new Inform();
		inform .setUserIds(getInformUserIds());
		inform.setWaitingForInform(waitingForInform);
		
		informList = new ArrayList<Inform>();
		informList.add(inform);
		return informList;
	}

	@Override
	public Set<String> getInformUserIds() {
		if(informUserIds != null){
			return informUserIds;
		}
		informUserIds = new HashSet<String>();
		informUserIds.addAll(replyMapper.getAllReplyUsers(replyInfo.getPostNo()));
		informUserIds.add(postMapper.getPostInfo(replyInfo.getPostNo()).getUserId());
		informUserIds.remove(replyInfo.getUserId());
		return informUserIds;
	}

}
