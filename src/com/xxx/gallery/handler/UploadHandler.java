package com.xxx.gallery.handler;

import org.jboss.netty.handler.codec.http.HttpMethod;

import com.xxx.common.server.Handler;
import com.xxx.common.server.NettyHttpRequest;
import com.xxx.gallery.dto.CommonFileDTO;
import com.xxx.gallery.queue.impl.ExifTaskServiceImpl;
import com.xxx.gallery.queue.impl.IndexTaskServiceImpl;
import com.xxx.gallery.queue.vo.ExifFileTask;
import com.xxx.gallery.queue.vo.IndexTask;
import com.xxx.gallery.resp.ImageUploadResp;
import com.xxx.gallery.service.ImageUploadService;
import com.xxx.taskQueue.service.ITaskService;
import com.xxx.util.CostTime;

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
			if(resp.getFileList() != null){
				for(CommonFileDTO cfile : resp.getFileList()){
					if(cfile.getFileMD5() == null){
						continue;
					}
					ITaskService taskService = new ExifTaskServiceImpl();
					taskService.addTask(new ExifFileTask(cfile.getFileMD5()));
					
					taskService = new IndexTaskServiceImpl();
					taskService.addTask(new IndexTask(cfile.getFileMD5()));
				}
			}
			break;
		default:
			return new ImageUploadResp("no this index:" + method, 400);
		}

		resp.costTime=cst.cost();
		return resp;
	}

	
}
