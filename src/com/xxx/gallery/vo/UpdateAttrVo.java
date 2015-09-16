package com.xxx.gallery.vo;

import java.util.Map;

public class UpdateAttrVo extends FileRequestVo {

	private Map<String, Object> attrJson;

	public UpdateAttrVo() {
		super();
	}

	public Map<String, Object> getAttrJson() {
		return attrJson;
	}

	public void setAttrJson(Map<String, Object> attrJson) {
		this.attrJson = attrJson;
	}

}
