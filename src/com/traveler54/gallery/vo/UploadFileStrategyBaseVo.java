package com.traveler54.gallery.vo;

import java.io.Serializable;
import java.util.List;

public class UploadFileStrategyBaseVo implements Serializable {

	/**
	 * 
	 */
	public static final long serialVersionUID = 3452132466367424581L;
	private String name;
	private long fileSizeMax;
	private long sizeMax;
	private boolean isNeedWaterMark;
	private String filePrefix;
	private String fileType;
	private List<String> suffix;
	private List<String> fileTypePreFix;

	public UploadFileStrategyBaseVo() {
	}

	public long getFileSizeMax() {
		return fileSizeMax;
	}

	public void setFileSizeMax(long fileSizeMax) {
		this.fileSizeMax = fileSizeMax;
	}

	public long getSizeMax() {
		return sizeMax;
	}

	public void setSizeMax(long sizeMax) {
		this.sizeMax = sizeMax;
	}

	public boolean isNeedWaterMark() {
		return isNeedWaterMark;
	}

	public void setNeedWaterMark(boolean isNeedWaterMark) {
		this.isNeedWaterMark = isNeedWaterMark;
	}

	public String getFilePrefix() {
		return filePrefix;
	}

	public void setFilePrefix(String filePrefix) {
		this.filePrefix = filePrefix;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getFileTypePreFix() {
		return fileTypePreFix;
	}

	public void setFileTypePreFix(List<String> fileTypePreFix) {
		this.fileTypePreFix = fileTypePreFix;
	}

	public List<String> getSuffix() {
		return suffix;
	}

	public void setSuffix(List<String> suffix) {
		this.suffix = suffix;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
