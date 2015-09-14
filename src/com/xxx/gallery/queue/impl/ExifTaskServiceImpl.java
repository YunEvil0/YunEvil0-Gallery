package com.xxx.gallery.queue.impl;

import com.xxx.gallery.queue.vo.ExifFileTask;
import com.xxx.gallery.service.ImageBisService;
import com.xxx.gallery.service.impl.ImageBisServiceImpl;
import com.xxx.taskQueue.service.ITaskService;
import com.xxx.taskQueue.service.impl.TaskBaseServiceImpl;
import com.xxx.taskQueue.vo.BaseTask;
import com.xxx.taskQueue.vo.BaseTaskResult;
import com.xxx.util.logging.ESLogger;
import com.xxx.util.logging.Loggers;

public class ExifTaskServiceImpl extends TaskBaseServiceImpl implements ITaskService{
	
	private static ESLogger log = Loggers.getLogger(ExifTaskServiceImpl.class);

	@Override
	public BaseTaskResult doTask(BaseTask task) {
		if(!(task instanceof ExifFileTask)){
			return null;
		}
		ExifFileTask exifTask = (ExifFileTask)task;
		ImageBisService bisService = new ImageBisServiceImpl();
		bisService.readExif(exifTask.getFileMd5());
		return null;
	}

}
