package com.xxx.gallery.vo;

abstract class FileRequestVo {

	private String fileMd5;
	private String uri;

	public FileRequestVo() {
		super();
	}

	public String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
