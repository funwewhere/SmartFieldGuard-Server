package com.szty.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.szty.bean.FieldInfo;
import com.szty.bean.my.FieldHistoryCriteria;
import com.szty.bean.my.VoFieldData;
import com.szty.enums.TimeScope;
import com.szty.service.FieldService;

public class FieldControllerTest {

	private ApplicationContext applicationContext;
	
	private FieldService fieldService;
	
	@Before
	public void setUp() throws Exception {
		this.applicationContext = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","applicationContext-shiro.xml","spring-mvc.xml"});
		fieldService = (FieldService) applicationContext.getBean("fieldServiceImpl");
	}

	@Test
	public void testAdd() throws Exception {
		FieldInfo fieldInfo = new FieldInfo();
		fieldInfo.setFieldName("农田一号");
		fieldInfo.setDeviceMac("macmacmacmacmacmac");
		fieldInfo.setFieldArea(60d);
		fieldInfo.setUserId("1");
		fieldInfo.setLongitude(66d);
//		fieldInfo.setLatitude(-44f);
//		fieldController.add(fieldInfo);
	}

	@Test
	public void TestgetHistoryData(){
		FieldHistoryCriteria criteria = new FieldHistoryCriteria();
		criteria.setCropNo("1");
		criteria.setTimeScope(TimeScope.Week);
		criteria.setNumber(1);
		criteria.setUserId("44444");
		criteria.setFieldNo("FD001605300002");
		List<VoFieldData> historyData = fieldService.analyzeHistoryData(criteria );
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		for(VoFieldData fieldData : historyData){
			System.out.println(JSONObject.fromObject(fieldData, jsonConfig).toString());
		}
	}
	
	public class JsonDateValueProcessor implements JsonValueProcessor {  
	    private String format ="yyyy-MM-dd HH:mm:ss";  
	      
	    public JsonDateValueProcessor() {  
	        super();  
	    }  
	      
	    public JsonDateValueProcessor(String format) {  
	        super();  
	        this.format = format;  
	    }  
	  
	    @Override  
	    public Object processArrayValue(Object paramObject,  
	            JsonConfig paramJsonConfig) {  
	        return process(paramObject);  
	    }  
	  
	    @Override  
	    public Object processObjectValue(String paramString, Object paramObject,  
	            JsonConfig paramJsonConfig) {  
	        return process(paramObject);  
	    }  
	      
	      
	    private Object process(Object value){  
	        if(value instanceof Date){    
	            SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.CHINA);    
	            return sdf.format(value);  
	        }    
	        return value == null ? "" : value.toString();    
	    }  
	  
	}  
}
