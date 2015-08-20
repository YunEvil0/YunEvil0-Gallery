package com.xxx.gallery.queue;

import com.xxx.gallery.service.ImageBisService;
import com.xxx.gallery.service.impl.ImageBisServiceImpl;
import com.xxx.taskQueue.service.ITaskService;
import com.xxx.taskQueue.service.impl.TaskBaseServiceImpl;
import com.xxx.taskQueue.vo.BaseTask;
import com.xxx.taskQueue.vo.BaseTaskResult;

public class ExifTaskServiceImpl extends TaskBaseServiceImpl implements ITaskService{

	@Override
	public BaseTaskResult doTask(BaseTask task) {
		System.out.println("[ExifTaskServiceImpl]START");
		if(!(task instanceof ExifFileTask)){
			return null;
		}
		ExifFileTask exifTask = (ExifFileTask)task;
		ImageBisService bisService = new ImageBisServiceImpl();
		bisService.readExif(exifTask.getFileMd5());
		System.out.println("[ExifTaskServiceImpl]END");
		return null;
	}

}
