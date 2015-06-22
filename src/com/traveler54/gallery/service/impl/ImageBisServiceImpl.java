package com.traveler54.gallery.service.impl;

import java.net.UnknownHostException;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.traveler54.gallery.service.ImageBisService;
import com.traveler54.gallery.vo.CommonFile;
import com.traveler54.gallery.vo.ImageBisInfoVo;
import com.traveler54.mongo.MongoUtil;

public class ImageBisServiceImpl implements ImageBisService{

	@Override
	public void fillUp(List<ImageBisInfoVo> bisInfoList) {
		if(bisInfoList == null || bisInfoList.size() == 0){
			return;
		}
		
		for(ImageBisInfoVo vo:bisInfoList){
			this.fillUp(vo);
		}
	}

	@Override
	public void fillUp(List<ImageBisInfoVo> bisInfoList,List<CommonFile> fileList) {
		if(bisInfoList == null || bisInfoList.size() < 1){
			return;
		}
		int infoSize = bisInfoList.size();
		for(int index=0;index<fileList.size() && index <= infoSize;index++){
			CommonFile cfile = fileList.get(index);
			ImageBisInfoVo infoVo = bisInfoList.get(index);
			if(infoVo == null){
				continue;
			}
			
			infoVo.setFileMd5(cfile.getFileMD5());
		}
		this.fillUp(bisInfoList);
		
	}

	@Override
	public void fillUp(ImageBisInfoVo bisInfo) {
		MongoUtil mongoUtil = null;
		try {
			mongoUtil = MongoUtil.getInstance();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		if(mongoUtil == null){
			return;
		}
		Datastore ds = mongoUtil.getDS();
		if(ds == null){
			return;
		}
		Query<CommonFile> query = ds.createQuery(CommonFile.class).field("fileMD5").equal(bisInfo.getFileMd5());
		UpdateOperations<CommonFile> ops = ds.createUpdateOperations(CommonFile.class).addAll("tagList",bisInfo.getTagList(),false);
		UpdateResults update = ds.update(query, ops);
	}

}
