package com.traveler54.taskQueue.service;

import com.traveler54.taskQueue.vo.BaseTask;
import com.traveler54.taskQueue.vo.BaseTaskResult;

public interface ITaskService {

	public boolean addTask(BaseTask task);
	
	public BaseTaskResult doTask(BaseTask task);
}
