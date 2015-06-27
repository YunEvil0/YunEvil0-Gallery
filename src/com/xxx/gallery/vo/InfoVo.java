package com.xxx.gallery.vo;

import java.util.List;
import java.util.Map;

public class InfoVo {

	private String fileMd5;
	private String title;
	private String uploader;// 上传者
	private String source;// 来源？什么意思 我忘了
	private String owner;// 版权所属
	private String permission;// 访问权限private or public
	private String copyright;// 版权开放信息
	private List<TagVo> tagList;
	private Map<String, Object> attr;

	public InfoVo() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<TagVo> getTagList() {
		return tagList;
	}

	public void setTagList(List<TagVo> tagList) {
		this.tagList = tagList;
	}

	public String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public Map<String, Object> getAttr() {
		return attr;
	}

	public void setAttr(Map<String, Object> attr) {
		this.attr = attr;
	}

}
