package com.xxx.gallery.handler;

import org.apache.commons.lang.StringUtils;
import org.jboss.netty.handler.codec.http.HttpMethod;

import com.alibaba.fastjson.JSON;
import com.xxx.common.server.Handler;
import com.xxx.common.server.NettyHttpRequest;
import com.xxx.gallery.queue.IndexTask;
import com.xxx.gallery.queue.IndexTaskServiceImpl;
import com.xxx.gallery.queue.OssFileTask;
import com.xxx.gallery.queue.OssFileTaskServiceImpl;
import com.xxx.gallery.resp.BisResp;
import com.xxx.gallery.service.ImageBisService;
import com.xxx.gallery.service.impl.ImageBisServiceImpl;
import com.xxx.gallery.vo.InfoVo;
import com.xxx.taskQueue.service.ITaskService;
import com.xxx.util.CostTime;

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
			
			InfoVo infoVo = JSON.parseObject(reqbody, InfoVo.class);
			
			resp = new BisResp();
			resp.setResult(this.bisService.fillUp(infoVo));
			resp.respCode = 200;

			ITaskService taskService = new OssFileTaskServiceImpl();
			taskService.addTask(new OssFileTask(infoVo));
			
			taskService = new IndexTaskServiceImpl();
			taskService.addTask(new IndexTask(infoVo.getFileMd5()));
			break;
		default:
			return new BisResp("no this index:" + method, false,(short) 404);
		}

		resp.costTime=cst.cost();
		return resp;
		
	}

	
}
