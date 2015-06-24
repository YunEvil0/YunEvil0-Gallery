package com.traveler54.gallery.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jboss.netty.handler.codec.http.HttpMethod;

import com.alibaba.fastjson.JSON;
import com.traveler54.common.server.Handler;
import com.traveler54.common.server.NettyHttpRequest;
import com.traveler54.gallery.resp.BisResp;
import com.traveler54.gallery.service.ImageBisService;
import com.traveler54.gallery.service.impl.ImageBisServiceImpl;
import com.traveler54.gallery.vo.InfoVo;
import com.traveler54.util.CostTime;

public class BisHandler implements Handler{
	private ImageBisService bisService = new ImageBisServiceImpl();

	@Override
	public BisResp handleRequest(NettyHttpRequest nettyRequest) {
		if(nettyRequest.getRequest().getMethod().equals(HttpMethod.OPTIONS)){
			BisResp resp = new BisResp();
			return resp;
		}
		String reqbody = nettyRequest.contentAsString();
		
		BisResp resp = null;
		
		CostTime cst = new CostTime();
		cst.start();
		
		String method = nettyRequest.param("method_name");
		switch(method){
		case "fillUp.do":
			if(StringUtils.isBlank(reqbody)){
				return new BisResp("PARAM_NULL", null,(short)404);
			}
			/*
			ITaskService taskService = new OssFileTaskServiceImpl();
			taskService.addTask(new OssFileTask(JSON.parseArray(reqbody, InfoVo.class)));
			*/
			
			List<InfoVo> infoList = JSON.parseArray(reqbody, InfoVo.class);
			List<String> fillUpFaild = new ArrayList<String>();
			for(InfoVo vo:infoList){
				boolean fillUp = this.bisService.fillUp(vo);
				if(!fillUp){
					fillUpFaild.add(vo.getFileMd5());
				}
			}
			resp = new BisResp();
			resp.setResult(fillUpFaild);
			resp.respCode = 400;
			break;
		default:
			return new BisResp("no this index:" + method, false,(short) 404);
		}

		resp.costTime=cst.cost();
		return resp;
		
	}

	
}
