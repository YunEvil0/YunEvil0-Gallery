package com.traveler54.gallery.vo;

import java.util.List;

public class ImageBisInfoVo {

	private String fileMd5;
	private String title;
	private List<ImageBisInfoTagVo> tagList;

	public ImageBisInfoVo() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ImageBisInfoTagVo> getTagList() {
		return tagList;
	}

	public void setTagList(List<ImageBisInfoTagVo> tagList) {
		this.tagList = tagList;
	}

	public String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}

}
