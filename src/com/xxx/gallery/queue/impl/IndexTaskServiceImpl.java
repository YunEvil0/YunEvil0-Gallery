package com.xxx.gallery.queue.impl;

import java.net.UnknownHostException;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.alibaba.fastjson.JSON;
import com.xxx.elasticsearch.util.ElasticSearchUtil;
import com.xxx.gallery.dto.CommonFileDTO;
import com.xxx.gallery.queue.vo.IndexTask;
import com.xxx.mongo.MongoUtil;
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
		try {
			Client client = ElasticSearchUtil.getInstance().getClient();
			
			Datastore ds = MongoUtil.getInstance().getDS();
			Query<CommonFileDTO> query = ds.find(CommonFileDTO.class).field("fileMD5").equal(indexTask.getFileMd5());
			CommonFileDTO file = query.get();
			System.out.println(JSON.toJSONString(file));
			IndexResponse indexResp = client.prepareIndex("ossfile", file.getGroup(),file.getFileMD5()).setSource(JSON.toJSONString(file)).execute().actionGet();
			System.out.println(JSON.toJSONString(indexResp));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}

}
