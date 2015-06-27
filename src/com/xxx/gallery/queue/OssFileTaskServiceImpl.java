package com.xxx.gallery.queue;

import java.util.List;

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
