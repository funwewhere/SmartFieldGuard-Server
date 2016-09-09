package com.szty.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    private Properties props;
    
    public PropertiesUtil(String filePath){
        readProperties(filePath);
    }
    
    private void readProperties(String filePath) {
    	Properties props = new Properties();
    	try {
    		//InputStream in = new BufferedInputStream (new FileInputStream(filePath));
    		InputStream in = PropertiesUtil.class.getResourceAsStream(filePath);
    		props.load(in);
//    		String value = props.getProperty (key);
//    		System.out.println("key:"+key+"   "+"value:"+value);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public String getProperty(String key) {
    	return props.getProperty(key);
    }
}
