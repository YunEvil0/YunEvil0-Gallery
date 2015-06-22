package com.traveler54.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 通过有状态码,读取conf.properties,获取状态信息
 * 不做缓存
 * @author EX-WANGXIAOYU590
 *
 */
public class StatusCodeUtil {
	private static Configuration config = null;
	static{
		init();
	}
	private static void init(){
		String resource = null;
		try {
			if(System.getProperty("os.name").toLowerCase().contains("windows")){
				resource = "conf/statusCode.properties";
			}else{
				resource = "../conf/statusCode.properties";
			}
			config = new PropertiesConfiguration(resource);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得Value，不做缓存
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		String errorMessage = config.getString(key);
		return errorMessage;
	}
	
}
