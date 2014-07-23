package com.godwin.tools;

import java.io.BufferedInputStream;   
import java.io.FileInputStream;   
import java.io.IOException;
import java.io.InputStream;   
import java.util.Properties;   


/**
 * 
 * read properties tool
 * @author 
 */
public class PropertiesConfig {
	
	private static final Properties prop = new Properties();
	private static final String SYSTEM_PROP_PATH = "Constant.properties";
	
	private static String PORT_RECEIVE_KEY = "PORT";
	
	
	
	public static Integer PORT_RECEIVE_VALUE = null;
	
	public static void initialPropFile(){
		try {
			InputStream inStream = new BufferedInputStream(new FileInputStream(SYSTEM_PROP_PATH));
			prop.load(inStream);
			inStream.close();
		} catch (IOException e) {
			LogUtils.getLoger().error("\r\nFailure to load Constant.properties file !");
		}
		
		PORT_RECEIVE_VALUE = Integer.parseInt(getPropValue(PORT_RECEIVE_KEY));
	}
	
    /**
     * get property value by param
     */
    public static String getPropValue(String param) {   
    	return (String) prop.get(param);
    }
    
}  
