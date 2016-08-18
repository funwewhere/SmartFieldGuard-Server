package com.szty.util;

import java.util.Random;

/**
 * 随机整数生成器
 * @author fwwer
 *
 */
public class RandomNumberUtil {

	/**
	 * 获取随机整数
	 * @param places 所需整数的位数
	 * @return 随机整数
	 */
	public static String getValidateCode(int places){
		int num = 10;
		
		for(int i = 1 ; i < places ; ++i){
			num *= 10;
		}
		
		String validateCode = "" + new Random().nextInt(num);
		
		for(int i = 0 ; i < places - validateCode.length() ; ++i){
			validateCode = "0" + validateCode;
		}
		
		return validateCode;
	}
	
}
