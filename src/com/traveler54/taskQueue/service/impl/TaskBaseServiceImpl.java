package com.traveler54.taskQueue.service.impl;

import com.traveler54.taskQueue.core.PoolManager;
import com.traveler54.taskQueue.service.ITaskService;
import com.traveler54.taskQueue.vo.BaseTask;
import com.traveler54.taskQueue.vo.BaseTaskResult;


public class TaskBaseServiceImpl implements ITaskService{
	
	private PoolManager poolManager;
	
	public TaskBaseServiceImpl(){
		this.poolManager = PoolManager.getInstance();
	}
	
	@Override
	public boolean addTask(BaseTask task){
		return this.poolManager.offer(task);
	}

	@Override
	public BaseTaskResult doTask(BaseTask task) {
		return null;
	}

}
