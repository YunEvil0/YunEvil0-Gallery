package com.xxx.gallery.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.xxx.gallery.exception.BisException;

public class UploadFileStrategyFactory {

	private static Map<String,UploadFileStrategyBaseVo> upStrategyMap;
	
	private static UploadFileStrategyFactory instance;
	
	private UploadFileStrategyFactory(){
		upStrategyMap=new HashMap<String,UploadFileStrategyBaseVo>();
	}
	
	public static UploadFileStrategyFactory getInstance(){
		if(instance==null){
			instance=new UploadFileStrategyFactory();
		}
		return instance;
	}
	
	private void addStrategy(String name,UploadFileStrategyBaseVo vo){
		upStrategyMap.put(name, vo);
	}

	public void addStrategy(String text){
		List<UploadFileStrategyBaseVo> list=JSON.parseArray(text, UploadFileStrategyBaseVo.class);
		for(UploadFileStrategyBaseVo vo:list){
			this.addStrategy(vo.getName(), vo);
		}
	}
	
	@SuppressWarnings("static-access")
	public UploadFileStrategyBaseVo getStrategy(String name){
		return this.upStrategyMap.get(name);
	}
	
}
