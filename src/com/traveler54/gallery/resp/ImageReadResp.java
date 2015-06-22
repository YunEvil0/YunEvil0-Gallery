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
public class ImageReadResp extends Resp {

	private static final long serialVersionUID = 1L;
	public long costTime;
	private String result;
	private String statusCode = "200";
	private String errorMessage;
	private byte[] data;

	public ImageReadResp() {

	}

	public ImageReadResp(String result) {
		this.result = result;
	}

	public ImageReadResp(String statusCode, String errorMessage, int httpStatus) {
		this.statusCode = statusCode;
		this.respCode = (short) httpStatus;
		this.errorMessage = errorMessage;
	}

	public ChannelBuffer toJson() {
		return ChannelBuffers.copiedBuffer(JSON.toJSONString(this),
				Charset.forName("UTF-8"));
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
