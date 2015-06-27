package com.xxx.gallery.queue;

import com.xxx.taskQueue.service.ITaskService;
import com.xxx.taskQueue.service.impl.TaskBaseServiceImpl;
import com.xxx.taskQueue.vo.BaseTask;
import com.xxx.taskQueue.vo.BaseTaskResult;

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
