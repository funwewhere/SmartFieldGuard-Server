package com.szty.mina;

import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.szty.bean.my.DeviceRequest;
import com.szty.bean.my.MobileRequest;

import net.sf.json.JSONObject;

public class HDecoder extends CumulativeProtocolDecoder {
	
	private Logger log = Logger.getLogger(HDecoder.class);
	
	private final Charset charset;

	public HDecoder(Charset charset) {
		this.charset = charset;
	}

	public boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out){
		log.info("in.....");
		try {
			if(in.remaining()<5){
				log.info("in.....<5");
				return false;
			}
			in.mark();
			byte a = in.get();
			log.info("a....." + a);
			if(a == 0x01){
				for(int i=0; i<3; ++i){
					in.get();
				}
				byte b = in.get();
				if(b <0 || b > in.remaining()){
					in.reset();
					return false;
				}else{
					byte[] bytes = new byte[b];
					in.get(bytes, 0, b);
					String lenStr = new String(bytes,charset);
					int len = Integer.parseInt(lenStr);
					log.info("len: " + len);
					if(len <0 || len > in.remaining()){
						in.reset();
						return false;
					} else {
						byte[] dataBytes = new byte[len];
						in.get(dataBytes, 0, len);
						String data = new String(dataBytes, charset);
						System.out.println("data: " + data);
						if("keep-live".equals(data)){
							session.write("keep-live\n");
						} else {
							JSONObject jsonObject = JSONObject.fromObject(data);
							DeviceRequest deviceData = (DeviceRequest) JSONObject.toBean(jsonObject, DeviceRequest.class);
							out.write(deviceData);
						}
						if(in.remaining() > 0){
							return true;
						}
					}
				}
			} else if(a == 0x02){
				log.info("iOS coming......");
				int length = in.getInt();
				log.info("iOS length......" + length);
				if(length <0 || length > in.remaining()){
					in.reset();
					return false;
				}
				byte[] dataBytes = new byte[length];
				in.get(dataBytes, 0, length);
				String data = new String(dataBytes, charset);
				log.info("iOS data: " + data);
				JSONObject jsonObject = JSONObject.fromObject(data);
				MobileRequest mobileData = (MobileRequest) JSONObject.toBean(jsonObject, MobileRequest.class);
				out.write(mobileData);
				if(in.remaining() > 0){
					return true;
				}
			}
		} catch (Exception e) {
			log.error("error...", e);
			e.printStackTrace();
		}
        return false;
	}
}
