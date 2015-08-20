package com.xxx.gallery.dto;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.NotSaved;
import org.mongodb.morphia.annotations.Transient;

@Entity(value="StorgeFile")
public class CommonFileDTO {
	private static final long serialVersionUID = -269949918665231386L;

	@Id
	private String fileMD5;
	private String ossName;
	private String filePath;
	private String group;
	@Embedded
	private BisAttrDTO bisAttr;
	@Embedded
	private FileAttrBaseDTO fileAttr;
	@NotSaved
	private String msg;

	public CommonFileDTO() {
	}

	public CommonFileDTO(String ossName, String filePath, String fileMD5,
			int size) {
		super();
		this.ossName = ossName;
		this.filePath = filePath;
		this.fileMD5 = fileMD5;
	}

	public CommonFileDTO(String msg) {
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

	public BisAttrDTO getBisAttr() {
		return bisAttr;
	}

	public void setBisAttr(BisAttrDTO bisAttr) {
		this.bisAttr = bisAttr;
	}

	public FileAttrBaseDTO getFileAttr() {
		return fileAttr;
	}

	public void setFileAttr(FileAttrBaseDTO fileAttr) {
		this.fileAttr = fileAttr;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	

}
