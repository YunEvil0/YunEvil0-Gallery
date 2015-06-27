package com.xxx.gallery.resp;

import java.nio.charset.Charset;
import java.util.List;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import com.alibaba.fastjson.JSON;
import com.xxx.common.server.resp.Resp;
import com.xxx.gallery.dto.CommonFileDTO;

/**
 * 
 * @author EX-WANGXIAOYU590
 *
 */
public class ImageUploadResp extends Resp {

	private static final long serialVersionUID = 1L;
	public long costTime;
	private String result;
	private String errorMessage;
	private List<CommonFileDTO> fileList;

	public ImageUploadResp() {

	}

	public ImageUploadResp(String result) {
		this.result = result;
	}

	public ImageUploadResp(String errorMessage, int httpStatus) {
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<CommonFileDTO> getFileList() {
		return fileList;
	}

	public void setFileList(List<CommonFileDTO> fileList) {
		this.fileList = fileList;
	}

	
}
