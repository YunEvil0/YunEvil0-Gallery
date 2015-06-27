package com.traveler54.gallery.dto;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;

import com.traveler54.gallery.vo.TagVo;

@Embedded
public class BisAttrDTO {

	private String title;//图片标题
	private String uploader;//上传者
	private String source;//来源？什么意思 我忘了
	private String owner;//版权所属
	private String permission;//访问权限private or public
	private String copyright;//版权开放信息
	private List<TagVo> tagList;

	public BisAttrDTO() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
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

	public List<TagVo> getTagList() {
		return tagList;
	}

	public void setTagList(List<TagVo> tagList) {
		this.tagList = tagList;
	}

}
