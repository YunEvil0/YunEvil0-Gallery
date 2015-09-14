package com.xxx.gallery.queue.impl;

import java.net.UnknownHostException;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.xxx.gallery.dto.CommonFileDTO;
import com.xxx.gallery.queue.vo.IndexTask;
import com.xxx.mongo.MongoUtil;
import com.xxx.taskQueue.core.PoolPullThread;
import com.xxx.taskQueue.service.ITaskService;
import com.xxx.taskQueue.service.impl.TaskBaseServiceImpl;
import com.xxx.taskQueue.vo.BaseTask;
import com.xxx.taskQueue.vo.BaseTaskResult;
import com.xxx.util.logging.ESLogger;
import com.xxx.util.logging.Loggers;

public class FullIndexTaskServiceImpl extends TaskBaseServiceImpl implements ITaskService {

	private static ESLogger log = Loggers.getLogger(PoolPullThread.class);
	
	@Override
	public BaseTaskResult doTask(BaseTask task) {
		ITaskService indexTaskService = new IndexTaskServiceImpl();

		Datastore ds = null;
		try {
			ds = MongoUtil.getInstance().getDS();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			log.error("msg:{},taskId:{}",e.getMessage(), task.getTaskId());
		}

		if(ds == null){
			return null;
		}
		
		int page = 0;
		boolean doLoopFlag = true;
		int count = 0;
		while (doLoopFlag) {
			Query<CommonFileDTO> query = ds.find(CommonFileDTO.class).offset(page * 100).limit(100);
			List<CommonFileDTO> asList = query.asList();
			if (asList == null || asList.size() == 0) {
				doLoopFlag = false;
				continue;
			}
			page++;
			for (CommonFileDTO cfile : asList) {
				indexTaskService.addTask(new IndexTask(cfile.getFileMD5()));
				count++;
			}
		}
		log.info("oss-size:{}", count);
		return null;
	}

}
