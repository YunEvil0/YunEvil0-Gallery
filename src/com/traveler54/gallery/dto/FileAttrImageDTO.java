package com.traveler54.gallery.dto;

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

}
