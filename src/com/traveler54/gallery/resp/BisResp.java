package com.traveler54.gallery.resp;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import com.alibaba.fastjson.JSON;
import com.traveler54.common.server.resp.Resp;

/**
 * 
 * @author EX-WANGXIAOYU590
 *
 */
public class BisResp extends Resp {

	private static final long serialVersionUID = 1L;
	public long costTime;
	private Object result;
	private String msg = "ok";

	public BisResp() {

	}

	public BisResp(boolean result) {
		this.result = result;
	}
	
	public BisResp(String msg,Object result,short respCode){
		this.result = result;
		this.msg = msg;
		this.respCode = respCode;
	}

	public ChannelBuffer toJson() {
		return ChannelBuffers.copiedBuffer(JSON.toJSONString(this),Charset.forName("UTF-8"));
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public long getCostTime() {
		return costTime;
	}

	public void setCostTime(long costTime) {
		this.costTime = costTime;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
