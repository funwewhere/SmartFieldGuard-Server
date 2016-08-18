package com.szty.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.ezmorph.object.AbstractObjectMorpher;

public class JsonToDateMorpher extends AbstractObjectMorpher {

	@Override
	public Object morph(Object value) {
		if (value instanceof String) {
			String source = (String) value;
			SimpleDateFormat simpleDateFormat = null;
			Date date = null;
			
			try {
				long time = Long.parseLong(source);
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
							return new Date();
						}
					}
				}
			}
	
			return date;
		}
		return null;
	}

	@Override
	public Class<Date> morphsTo() {
		return Date.class;
	}

}
