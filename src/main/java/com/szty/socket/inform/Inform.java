package com.szty.socket.inform;

import java.util.Set;

import com.szty.bean.WaitingForInform;

public class Inform {
	
	private Set<String> userIds;
	
	private WaitingForInform waitingForInform;

	public Set<String> getUserIds() {
		return userIds;
	}

	public void setUserIds(Set<String> userIds) {
		this.userIds = userIds;
	}

	public WaitingForInform getWaitingForInform() {
		return waitingForInform;
	}

	public void setWaitingForInform(WaitingForInform waitingForInform) {
		this.waitingForInform = waitingForInform;
	}
}
