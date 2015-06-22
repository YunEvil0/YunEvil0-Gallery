package com.traveler54.gallery.vo;

import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.NotSaved;

@Entity("StorgeFile")
public class CommonFile {
	private static final long serialVersionUID = -269949918665231386L;

	@Id
	private String fileMD5;
	private String ossName;
	private String filePath;
	private int size;
	private List<ImageBisInfoTagVo> tagList;
	@NotSaved
	private String msg;

	public CommonFile() {
		super();
	}
	
	public CommonFile(String ossName, String filePath,
			String fileMD5, int size) {
		super();
		this.ossName = ossName;
		this.filePath = filePath;
		this.fileMD5 = fileMD5;
		this.size = size;
	}



	public CommonFile(String msg) {
		this.msg = msg;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileMD5() {
		return fileMD5;
	}

	public void setFileMD5(String fileMD5) {
		this.fileMD5 = fileMD5;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOssName() {
		return ossName;
	}

	public void setOssName(String ossName) {
		this.ossName = ossName;
	}

	public List<ImageBisInfoTagVo> getTagList() {
		return tagList;
	}

	public void setTagList(List<ImageBisInfoTagVo> tagList) {
		this.tagList = tagList;
	}

}
