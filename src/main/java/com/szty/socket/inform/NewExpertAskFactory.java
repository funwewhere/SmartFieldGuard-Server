package com.szty.socket.inform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szty.bean.PostInfo;
import com.szty.bean.ReplyInfo;
import com.szty.bean.WaitingForInform;
import com.szty.bean.my.VoPost;
import com.szty.bean.my.VoReply;
import com.szty.enums.InformType;
import com.szty.mapper.my.PostMapper;
import com.szty.mapper.my.ReplyMapper;

@Component
public class NewExpertAskFactory extends AbstractInformFactory{
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Autowired
	private PostMapper postMapper;
	
	private PostInfo postInfo;
	
	private String expertId;
	
	public NewExpertAskFactory() {
		super(InformType.NewExpertAsk, true);
	}

	public void init(PostInfo postInfo, String expertId) {
		data = null;
		informList = null;
		informUserIds = null;
		this.expertId = expertId;
		this.postInfo = postInfo;
		createInforms();
	}

	@Override
	public Map<String, Object> createData(List<WaitingForInform> informs) {
		if(informs == null){
			informs = new ArrayList<WaitingForInform>();
			informs.add(createInforms().get(0).getWaitingForInform());
		}
		data = new HashMap<String, Object>();
		List<VoPost> posts = new ArrayList<VoPost>();
		for(WaitingForInform inform : informs){
			VoPost post = postMapper.getPostInfo(inform.getNo());
			posts.add(post);
		}
		data.put("posts", posts);
		return data;
	}

	@Override
	public List<Inform> createInforms() {
		if(informList != null){
			return informList;
		}
		WaitingForInform waitingForInform = new WaitingForInform();
		waitingForInform.setCreateDate(postInfo.getCreateDate());
		waitingForInform.setNo(postInfo.getPostNo());
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
		informUserIds.add(expertId);
		return informUserIds;
	}

}
