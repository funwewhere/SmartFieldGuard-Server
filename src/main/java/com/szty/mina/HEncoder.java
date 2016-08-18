package com.szty.mina;

import java.nio.charset.Charset;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.szty.bean.my.MobileResponse;


/**
 * @Description: 编码工具类
 * @author whl
 * @date 2014-9-30 下午12:35:35
 *
 */
public class HEncoder implements ProtocolEncoder {
	
	private final static Log log = LogFactory.getLog(HEncoder.class);
	
	private final Charset charset;

	public HEncoder(Charset charset) {
		this.charset = charset;
	}

	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
		if(message instanceof MobileResponse){
			MobileResponse data = (MobileResponse) message;
			byte a = 0x03;
			buffer.put(a);
			buffer.putInt(data.getData().getBytes().length);
			System.out.println("IOS response user" + session.getAttribute("key"));
			System.out.println("IOS response length" + data.getData().getBytes().length);
			System.out.println("IOS response data:" + data.getData());
			buffer.putString(data.getData(), charset.newEncoder());
		} else {
			System.out.println("response data:" + message);
			buffer.putObject(message);
		}
        buffer.flip();
        out.write(buffer);
	}

	@Override
	public void dispose(IoSession session) throws Exception {
		log.info("Dispose called,session is " + session);
	}
}
