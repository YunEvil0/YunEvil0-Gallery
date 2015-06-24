package com.traveler54.gallery.queue;

import com.traveler54.taskQueue.service.ITaskService;
import com.traveler54.taskQueue.service.impl.TaskBaseServiceImpl;
import com.traveler54.taskQueue.vo.BaseTask;
import com.traveler54.taskQueue.vo.BaseTaskResult;

public class IndexTaskServiceImpl extends TaskBaseServiceImpl implements ITaskService{

	@Override
	public BaseTaskResult doTask(BaseTask task) {
		if(!(task instanceof IndexTask)){
			return null;
		}
		IndexTask indexTask = (IndexTask)task;
		indexTask.getFileMd5();
		
		return null;
	}

}
