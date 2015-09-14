package com.xxx.gallery.queue.impl;

import java.util.List;

import com.xxx.gallery.queue.vo.OssFileTask;
import com.xxx.gallery.service.ImageBisService;
import com.xxx.gallery.service.impl.ImageBisServiceImpl;
import com.xxx.gallery.vo.InfoVo;
import com.xxx.taskQueue.service.ITaskService;
import com.xxx.taskQueue.service.impl.TaskBaseServiceImpl;
import com.xxx.taskQueue.vo.BaseTask;
import com.xxx.taskQueue.vo.BaseTaskResult;

public class OssFileTaskServiceImpl extends TaskBaseServiceImpl implements ITaskService{

	@Override
	public BaseTaskResult doTask(BaseTask task) {
		System.out.println("[OssFileTaskServiceImpl]START");
		if(!(task instanceof OssFileTask)){
			return null;
		}
		OssFileTask ossTask = (OssFileTask)task;
		List<InfoVo> bisInfoList = ossTask.getBisInfoList();
		ImageBisService bisService = new ImageBisServiceImpl();
		bisService.fillUp(bisInfoList);
		System.out.println("[OssFileTaskServiceImpl]END");
		return null;
	}

}
