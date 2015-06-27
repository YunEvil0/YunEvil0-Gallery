package com.xxx.taskQueue.service;

import com.xxx.taskQueue.vo.BaseTask;
import com.xxx.taskQueue.vo.BaseTaskResult;

public interface ITaskService {

	public boolean addTask(BaseTask task);
	
	public BaseTaskResult doTask(BaseTask task);
}
