package com.xxx.gallery.handler;

import java.io.FileInputStream;

import com.xxx.common.server.Handler;
import com.xxx.common.server.NettyHttpRequest;
import com.xxx.gallery.resp.ImageReadResp;
import com.xxx.util.CostTime;

public class ImageReadHandler implements Handler{

	@Override
	public ImageReadResp handleRequest(NettyHttpRequest nettyRequest) {
		ImageReadResp resp = new ImageReadResp();
		CostTime cst = new CostTime();
		cst.start();

		String uri = nettyRequest.getRequest().getUri();
		
		String[] splitUri = uri.split("\\?");
		
		if(splitUri.length==1){
			FileInputStream fis;
			try {
				fis = new FileInputStream("/Data/YunEvil0/Mine/IMG_0091.JPG");
				int size = fis.available();
				byte data[]=new byte[size];
				fis.read(data);
				resp.setData(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		resp.costTime=cst.cost();
		return resp;
		
	}

	
}
