package com.xxx.taskQueue.vo;

import com.xxx.taskQueue.service.ITaskService;

public abstract class BaseTask {

	protected ITaskService taskService;

	public BaseTask() {
		super();
	}

	public ITaskService getTaskService() {
		return taskService;
	}

}
