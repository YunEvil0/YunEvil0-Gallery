package com.xxx.taskQueue.vo;

import com.xxx.taskQueue.service.ITaskService;

public abstract class BaseTask {

	protected ITaskService taskService;
	protected String taskId;

	public BaseTask() {
		this.taskId = System.currentTimeMillis() + "-"
				+ Thread.currentThread().getId();
	}

	public ITaskService getTaskService() {
		return taskService;
	}

	public String getTaskId() {
		return taskId;
	}

}
