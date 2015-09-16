package com.xxx.gallery.handler;

import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xxx.common.server.Handler;
import com.xxx.common.server.NettyHttpRequest;
import com.xxx.gallery.dto.CommonFileDTO;
import com.xxx.gallery.exception.BisException;
import com.xxx.gallery.queue.impl.FullIndexTaskServiceImpl;
import com.xxx.gallery.queue.impl.IndexTaskServiceImpl;
import com.xxx.gallery.queue.vo.IndexTask;
import com.xxx.gallery.resp.BisResp;
import com.xxx.gallery.service.ImageBisService;
import com.xxx.gallery.service.impl.ImageBisServiceImpl;
import com.xxx.gallery.vo.AppendTagVo;
import com.xxx.gallery.vo.InfoVo;
import com.xxx.gallery.vo.UpdateAttrVo;
import com.xxx.mongo.MongoUtil;
import com.xxx.taskQueue.service.ITaskService;
import com.xxx.util.CostTime;
import com.xxx.util.logging.ESLogger;
import com.xxx.util.logging.Loggers;

public class BisHandler implements Handler {
	private ImageBisService bisService = new ImageBisServiceImpl();

	private static ESLogger log = Loggers.getLogger(BisHandler.class);

	@Override
	public BisResp handleRequest(NettyHttpRequest nettyRequest) {
		if (nettyRequest.getRequest().getMethod().equals(HttpMethod.OPTIONS)) {
			BisResp resp = new BisResp();
			return resp;
		}
		String reqbody = nettyRequest.contentAsString();

		BisResp resp = null;

		CostTime cst = new CostTime();
		cst.start();

		String method = nettyRequest.param("method_name");
		try {
			switch (method) {
			case "fillUp.do":
				resp = this.fillUp(reqbody);
				break;
			case "fullIndex.do":
				resp = this.fullIndex();
				break;
			case "appendTag.do":
				resp = this.appendTag(reqbody);
				break;
			case "updateAttr.do":
				resp = this.updateAttr(reqbody);
				break;
			default:
				return new BisResp("no this index:" + method, false,
						(short) 404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp = new BisResp();
			resp.respCode = 400;
			resp.setResult(false);
			resp.setMsg(e.getMessage());
			return resp;
		}

		resp.costTime = cst.cost();
		return resp;

	}

	private BisResp fillUp(String reqbody) {
		if (StringUtils.isBlank(reqbody)) {
			return new BisResp("PARAM_NULL", null, (short) 404);
		}

		List<InfoVo> infoList = JSON.parseArray(reqbody, InfoVo.class);

		ITaskService taskService = new IndexTaskServiceImpl();
		for (InfoVo vo : infoList) {
			this.bisService.fillUp(vo);
			taskService.addTask(new IndexTask(vo.getFileMd5()));
		}
		BisResp resp = new BisResp();
		resp.setResult(true);
		resp.respCode = 200;

		return resp;
	}

	private BisResp fullIndex() {
		ITaskService taskService = new FullIndexTaskServiceImpl();
		taskService.addTask(null);
		BisResp resp = new BisResp();
		resp.setResult("task queue doing");
		resp.respCode = 200;
		return resp;
	}

	private BisResp appendTag(String reqbody) {
		BisResp resp = null;
		List<AppendTagVo> voList = JSON.parseArray(reqbody, AppendTagVo.class);
		// AppendTagVo vo = JSON.parseObject(reqbody, AppendTagVo.class);
		if (voList == null) {
			resp = new BisResp();
			resp.setResult(false);
			resp.setMsg("PARAM_ERROR");
			return resp;
		}
		ITaskService taskService = new IndexTaskServiceImpl();
		for (AppendTagVo vo : voList) {
			try {
				
				if (vo == null) {
					continue;
				}
				if (vo.getFileMd5() != null && StringUtils.isNotBlank(vo.getFileMd5())) {
					boolean appendTag = this.bisService.appendTag(vo.getFileMd5(),vo.getTagList());
					taskService.addTask(new IndexTask(vo.getFileMd5()));
					continue;
				}

				if (vo.getUri() == null || StringUtils.isNotBlank(vo.getUri())) {
					continue;
				}

				Datastore ds = MongoUtil.getInstance().getDS();
				Query<CommonFileDTO> query = ds.find(CommonFileDTO.class).field("filePath").equal(vo.getUri());
				CommonFileDTO existFile = query.get();
				if (existFile == null) {
					continue;
				}
				boolean appendTag = this.bisService.appendTag(existFile.getFileMD5(),vo.getTagList());
				taskService.addTask(new IndexTask(existFile.getFileMD5()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		resp = new BisResp();
		resp.setResult(true);
		return resp;
	}

	private BisResp updateAttr(String reqbody){
		List<UpdateAttrVo> attrVoList = JSON.parseArray(reqbody, UpdateAttrVo.class);
		for(UpdateAttrVo attrVo:attrVoList){
			Datastore ds;
			try {
				ds = MongoUtil.getInstance().getDS();
				Query<CommonFileDTO> query = ds.find(CommonFileDTO.class);
				if(!StringUtils.isBlank(attrVo.getFileMd5())){
					query.field("fileMD5").equal(attrVo.getFileMd5());
				}else if(!StringUtils.isBlank(attrVo.getUri())){
					query.field("filePath").equal(attrVo.getUri());
				}
				CommonFileDTO existFile = query.get();

				if(existFile==null){
					continue;
				}
				
				Set<String> keySet = attrVo.getAttrJson().keySet();
				Iterator<String> it = keySet.iterator();
				UpdateOperations<CommonFileDTO> ops = ds.createUpdateOperations(CommonFileDTO.class);
				while(it.hasNext()){
					String key = it.next();
					ops.set(key,attrVo.getAttrJson().get(key));
				}
				UpdateResults update = ds.update(query, ops);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new BisResp(true);
	}
}
