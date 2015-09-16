package com.xxx.gallery.vo;

import java.util.List;

public class AppendTagVo extends FileRequestVo {

	private List<TagVo> tagList;

	public AppendTagVo() {
		super();
	}

	public List<TagVo> getTagList() {
		return tagList;
	}

	public void setTagList(List<TagVo> tagList) {
		this.tagList = tagList;
	}

}
