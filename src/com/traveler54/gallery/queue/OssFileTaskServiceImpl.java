package com.traveler54.gallery.queue;

import java.util.List;

import com.traveler54.gallery.service.ImageBisService;
import com.traveler54.gallery.service.impl.ImageBisServiceImpl;
import com.traveler54.gallery.vo.InfoVo;
import com.traveler54.taskQueue.service.ITaskService;
import com.traveler54.taskQueue.service.impl.TaskBaseServiceImpl;
import com.traveler54.taskQueue.vo.BaseTask;
import com.traveler54.taskQueue.vo.BaseTaskResult;

public class OssFileTaskServiceImpl extends TaskBaseServiceImpl implements ITaskService{

	@Override
	public BaseTaskResult doTask(BaseTask task) {
		if(!(task instanceof OssFileTask)){
			return null;
		}
		OssFileTask ossTask = (OssFileTask)task;
		List<InfoVo> bisInfoList = ossTask.getBisInfoList();
		ImageBisService bisService = new ImageBisServiceImpl();
		bisService.fillUp(bisInfoList);
		return null;
	}

}
