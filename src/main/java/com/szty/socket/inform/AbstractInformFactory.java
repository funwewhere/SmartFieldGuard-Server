package com.szty.socket.inform;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.szty.bean.WaitingForInform;
import com.szty.enums.InformType;

public abstract class AbstractInformFactory {
	
	/**
	 * 是否一定要通知到
	 */
	private boolean force;
	
	/**
	 * 通知类型
	 */
	protected InformType type;
	
	/**
	 * 需要通知的对象
	 */
	protected Set<String> informUserIds;

	/**
	 * 
	 */
	protected List<Inform> informList;

	/**
	 * 通知的信息
	 */
	protected Map<String, Object> data;
	
	protected boolean outOfDate;

	public boolean isForce() {
		this.outOfDate = true;
		return force;
	}
	
	public AbstractInformFactory(InformType informType, boolean force) {
		this.type = informType;
		this.force = force;
	}

	public abstract Map<String, Object> createData(List<WaitingForInform> informList);

	public abstract List<Inform> createInforms();

	public abstract Set<String> getInformUserIds();

	public InformType getType() {
		return type;
	}

	public boolean isOutOfDate() {
		return outOfDate;
	}
	
}
