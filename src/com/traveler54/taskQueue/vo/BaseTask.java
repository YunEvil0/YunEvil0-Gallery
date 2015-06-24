package com.traveler54.taskQueue.vo;

import com.traveler54.taskQueue.service.ITaskService;

public abstract class BaseTask {

	protected ITaskService taskService;

	public BaseTask() {
		super();
	}

	public ITaskService getTaskService() {
		return taskService;
	}

}
