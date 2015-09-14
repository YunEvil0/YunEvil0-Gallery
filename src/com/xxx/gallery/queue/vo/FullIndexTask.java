package com.xxx.gallery.queue.vo;

import com.xxx.gallery.queue.impl.FullIndexTaskServiceImpl;
import com.xxx.taskQueue.vo.BaseTask;

public class FullIndexTask extends BaseTask{

	public FullIndexTask() {
		super();
		this.taskService = new FullIndexTaskServiceImpl();
	}

}
