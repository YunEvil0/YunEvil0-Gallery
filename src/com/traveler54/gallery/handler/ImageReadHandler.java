package com.traveler54.gallery.handler;

import java.io.FileInputStream;

import com.traveler54.common.server.Handler;
import com.traveler54.common.server.NettyHttpRequest;
import com.traveler54.gallery.resp.ImageReadResp;
import com.traveler54.util.CostTime;

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
