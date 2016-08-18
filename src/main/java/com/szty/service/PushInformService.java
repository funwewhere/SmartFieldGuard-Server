package com.szty.service;

import com.szty.socket.inform.AbstractInformFactory;

public interface PushInformService {

	/**
	 * 群发
	 * @param inform
	 * @param currentUserId
	 * @throws Exception
	 */
	public void pushToUsers(AbstractInformFactory informFactory) throws Exception;

	/**
	 * 清除成功的通知
	 * @param sendUser
	 * @param informKey
	 * @throws Exception
	 */
	public void clearSuccessInform(String sendUser, String informKey) throws Exception;

	/**
	 * 持久化失败的通知
	 * @throws Exception
	 */
	public void insertFailInfrom() throws Exception;
	
	/**
	 * 用户上线将所有通知发给用户
	 */
	public void pushAllInformToUsers(String userId) throws Exception;

}
