package com.xxx.taskQueue.service.impl;

import com.xxx.taskQueue.core.PoolManager;
import com.xxx.taskQueue.service.ITaskService;
import com.xxx.taskQueue.vo.BaseTask;
import com.xxx.taskQueue.vo.BaseTaskResult;


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
