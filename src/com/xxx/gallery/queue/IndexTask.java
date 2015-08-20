package com.xxx.gallery.queue;

import com.xxx.taskQueue.vo.BaseTask;

public class IndexTask extends BaseTask{

	private String fileMd5;

	public IndexTask() {
		super();
	}

	public IndexTask(String fileMd5) {
		super();
		this.fileMd5 = fileMd5;
		this.taskService = new IndexTaskServiceImpl();
	}

	public String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}

}
