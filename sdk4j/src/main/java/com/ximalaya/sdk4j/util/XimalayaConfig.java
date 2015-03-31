package com.ximalaya.sdk4j.util;

import java.io.IOException;
import java.util.Properties;

public class XimalayaConfig {
	private static Properties props = new Properties(); 
	static{
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public XimalayaConfig(){}
	
	public static String getValue(String key){
		return props.getProperty(key);
	}
}