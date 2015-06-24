package com.traveler54.gallery.queue;

import com.traveler54.taskQueue.vo.BaseTask;

public class IndexTask extends BaseTask{

	private String fileMd5;

	public IndexTask() {
		super();
	}

	public IndexTask(String fileMd5) {
		this.fileMd5 = fileMd5;
	}

	public String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}

}
