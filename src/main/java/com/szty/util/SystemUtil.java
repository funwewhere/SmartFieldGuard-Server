package com.szty.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.szty.enums.Table;
import com.szty.mapper.my.SystemMapper;

/**
 * 系统工具类
 * @author fwwer 2015-11-23
 */
@Component
public class SystemUtil {
	
	@Autowired
	private SystemMapper systemMapper;
	
	private final String VALIDATE_CODE_URL = "http://106.ihuyi.cn/webservice/sms.php?method=Submit&account=cf_fww&password=478505231dou&";
	
	/**
	 * 主键：  SO    01    151123   0000
	 *     表标识     进位     创建时间    流水号（流水号0~9999，满10000进位加一）
	 * 根据表名生成最新主键
	 * @param table 需要主键的数据库表
	 * @return 所生成的主键
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public String gerneratorKey(Table table){
		
		//去数据库中去当前最大值
		String key = systemMapper.getAndLockTableKey(table.name());
		
		//取到当前key中的时间和当前时间
		String date = key.substring(4, 10);
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		
		//判断取到的两个时间是否为同一天
		if(today.equals(date)) {
			//主键加一
			key = increaseKey(key);
		} else {
			//重置主键为当天的第一个主键
			key = StringUtils.overlay(key, "00", 2, 4);
			key = StringUtils.overlay(key, today, 4, 10);
			key = StringUtils.overlay(key, "0000", 10, 14);
		}
		
		//key写回数据库.......
		systemMapper.updateTableKey(table.name(), key);
		
		return key;
	}

	/**
	 * 主键加一
	 * @param key 当前主键值
	 * @return 加一后的主键值
	 */
	private static String increaseKey(String key) {
		//取得流水号
		int num = Integer.parseInt(key.substring(10, 14));
		//流水号+1，若＜10000，则将更新主键的流水号为新流水号，否则，主键进位加一，主键流水号更新为0000
		if(++num < 10000){
			String strnum = Integer.toString(num);
			for(int i = strnum.length(); i < 4; i++){
				strnum = "0" + strnum;
			}
			key = StringUtils.overlay(key, strnum, 10, 14);
		} else {
			num = Integer.parseInt(key.substring(2, 4));
			++num;
			String strnum = Integer.toString(num);
			for(int i = strnum.length(); i < 2; i++){
				strnum = "0" + strnum;
			}
			key = StringUtils.overlay(key, strnum, 2, 4);
			key = StringUtils.overlay(key, "0000", 10, 14);
		}
		return key;
	}
	
	public String sentValidateCode(String tel) throws Exception {
		String validateCode = RandomNumberUtil.getValidateCode(6);
		
		//TODO
		validateCode = "123456";
		
		String sentUrl = VALIDATE_CODE_URL + "mobile=" + tel + "&content=您的验证码是：【" + validateCode + "】。请不要把验证码泄露给其他人。";
		System.out.println("validateCode:" + validateCode);
		System.out.println("sentUrl:" + sentUrl);
		
//		URLConnection connection = new URL(sentUrl).openConnection();
//		connection.getInputStream();
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
//		System.out.println("sentValidateCode result : " + bufferedReader.readLine());
//		bufferedReader.close();
		return validateCode;
	}
	
	public byte[] readBytes(InputStream is, int contentLen) {
        if (contentLen > 0) {
                int readLen = 0;
                int readLengthThisTime = 0;
                byte[] message = new byte[contentLen];
                try {
                    while (readLen != contentLen) {

                            readLengthThisTime = is.read(message, readLen, contentLen
                                            - readLen);

                            if (readLengthThisTime == -1) {// Should not happen.
                                    break;
                            }

                            readLen += readLengthThisTime;
                    }
                    return message;
                } catch (IOException e) {
                	e.printStackTrace();
                }
        }
        return new byte[] {};
	}

	
}
