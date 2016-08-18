package com.szty.mina;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * @Description: 编码和解码器工厂类.
 * @author whl
 * @date 2014-9-30 下午12:34:59
 *
 */
public class HCoderFactory implements ProtocolCodecFactory {
	
	private final HEncoder encoder;
	
	private final HDecoder decoder;

	public HCoderFactory() {
		this(Charset.forName("UTF-8"));

	}

	public HCoderFactory(Charset charSet) {
		this.encoder = new HEncoder(charSet);
		this.decoder = new HDecoder(charSet);
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		return decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		return encoder;
	}
}
