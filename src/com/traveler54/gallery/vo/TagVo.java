package com.traveler54.gallery.vo;

import org.mongodb.morphia.annotations.Entity;

@Entity(noClassnameStored = true)
public class TagVo {
	private String tagContent;
	private String tagFrom;
	private String tagType;

	public TagVo() {
		super();
	}

	public String getTagContent() {
		return tagContent;
	}

	public void setTagContent(String tagContent) {
		this.tagContent = tagContent;
	}

	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public String getTagFrom() {
		return tagFrom;
	}

	public void setTagFrom(String tagFrom) {
		this.tagFrom = tagFrom;
	}

}
