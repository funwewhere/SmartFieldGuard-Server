package com.szty.mina;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.szty.socket.handler.SocketHandler;

/**
 * Socket服务器端
 */
public class SocketServerHandler extends IoHandlerAdapter {
	
	private static final Logger log = Logger.getLogger(SocketServerHandler.class);
	
	@Autowired
	private SocketHandler socketHandler;
	
    @Override
    public void sessionOpened(IoSession session) throws Exception {
    	log.info("sessionOpened");
    	super.sessionOpened(session);
    }
   
    /**
     * 消息接收事件
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
    	socketHandler.processMessage(message, session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
    	log.info("sessionIdle  IDLE" + session.getIdleCount(status));
    }
    
    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
    		throws Exception {
    	log.info("exception caught!!");
    	super.exceptionCaught(session, cause);
    }
    
	@Override
	public void inputClosed(IoSession session) throws Exception {
		log.info("inputClosed");
		super.inputClosed(session);
	}
	
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		log.info("sessionClosed");
		//移除map里的session
		socketHandler.removeSession(session);
		session.closeOnFlush();
		super.sessionClosed(session);
	}
}
