package com.xxx.gallery.queue.vo;

import com.xxx.gallery.queue.impl.ExifTaskServiceImpl;
import com.xxx.taskQueue.vo.BaseTask;

public class ExifFileTask extends BaseTask {

	private String fileMd5;

	public ExifFileTask() {
		super();
	}

	public ExifFileTask(String fileMd5) {
		super();
		this.fileMd5 = fileMd5;
		this.taskService = new ExifTaskServiceImpl();
	}

	public String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}

}
