package com.traveler54.gallery.handler;

import org.jboss.netty.handler.codec.http.HttpMethod;

import com.traveler54.common.server.Handler;
import com.traveler54.common.server.NettyHttpRequest;
import com.traveler54.gallery.resp.ImageUploadResp;
import com.traveler54.gallery.service.ImageUploadService;
import com.traveler54.util.CostTime;

public class UploadHandler implements Handler{

	@Override
	public ImageUploadResp handleRequest(NettyHttpRequest nettyRequest) {
		if(nettyRequest.getRequest().getMethod().equals(HttpMethod.OPTIONS)){
			ImageUploadResp resp = new ImageUploadResp();
			return resp;
		}
		
		ImageUploadResp resp = null;
		CostTime cst = new CostTime();
		cst.start();
		String method = nettyRequest.param("method_name");
		switch(method){
		case "uploadFile.do":
			ImageUploadService action=new ImageUploadService();
			resp=action.doAction(nettyRequest);
			resp.getFileList();
			break;
		default:
			return new ImageUploadResp("no this index:" + method, 400);
		}

		resp.costTime=cst.cost();
		return resp;
		
	}

	
}
