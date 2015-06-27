package com.xxx.gallery.dto;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public abstract class FileAttrBaseDTO {

	private int size;
	private String suffix;
	private String format;// JPG?PNG?PDF
	private String applicationType;// IMAGE/DOCUMENT

	public FileAttrBaseDTO() {
		super();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

}
