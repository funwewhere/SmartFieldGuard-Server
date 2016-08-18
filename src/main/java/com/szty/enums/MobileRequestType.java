package com.szty.enums;

/**
 * 移动端请求类型（socket）
 * @author fww
 *
 */
public enum MobileRequestType {
	NewConnect,//新连接
	KeepLive,//心跳包
	SendMessage,//发送消息
	GetInform,//收到通知
	upDateFieldData//更新农田数据
}
