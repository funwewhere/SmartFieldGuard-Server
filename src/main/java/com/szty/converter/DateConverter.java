package com.szty.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 
 * @author fwwer
 *自定义日期类型转换的类
 *实现将日期串转成日期格式（yyyy-MM-dd HH:mm:ss）
 */

public class DateConverter implements Converter<String,Date> {

	@Override
	public Date convert(String source) {
		
		SimpleDateFormat simpleDateFormat = null;
		Date date = null;
		
		try {
			long time = Long.parseLong(source);
			if (time < 100000000000l) {
				time *= 1000;
			}
			date = new Date(time);
		} catch (Exception e) {
			try {
				simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				date = simpleDateFormat.parse(source);
			} catch (ParseException e1) {
				try {
					simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					date = simpleDateFormat.parse(source);
				} catch (ParseException e2) {
					try {
						simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
						date = simpleDateFormat.parse(source);
					} catch (ParseException e3) {
						e3.printStackTrace();
					}
				}
			}
		}
		
		return date;
	}
	
}
