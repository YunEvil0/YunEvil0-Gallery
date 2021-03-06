package com.xxx.gallery.dto;

import java.util.Map;

import org.mongodb.morphia.annotations.Embedded;

/**
 * EXIF
 * 
 * @author YunEvil0
 *
 */
@Embedded
public class FileAttrImageDTO extends FileAttrBaseDTO {

	private int width;
	private int height;
	private Map<String, String> exif;

	public FileAttrImageDTO() {
		super();
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Map<String, String> getExif() {
		return exif;
	}

	public void setExif(Map<String, String> exif) {
		this.exif = exif;
	}

}
