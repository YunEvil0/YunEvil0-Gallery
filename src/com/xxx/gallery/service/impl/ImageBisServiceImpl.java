package com.xxx.gallery.service.impl;

import java.net.UnknownHostException;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.xxx.gallery.dto.BisAttrDTO;
import com.xxx.gallery.dto.CommonFileDTO;
import com.xxx.gallery.service.ImageBisService;
import com.xxx.gallery.vo.InfoVo;
import com.xxx.mongo.MongoUtil;

public class ImageBisServiceImpl implements ImageBisService{

	@Override
	public void fillUp(List<InfoVo> bisInfoList) {
		if(bisInfoList == null || bisInfoList.size() == 0){
			return;
		}
	
		for(InfoVo vo:bisInfoList){
			this.fillUp(vo);
		}
		
	}

	@Override
	public void fillUp(List<InfoVo> bisInfoList,List<CommonFileDTO> fileList) {
		if(bisInfoList == null || bisInfoList.size() < 1){
			return;
		}
		int infoSize = bisInfoList.size();
		for(int index=0;index<fileList.size() && index <= infoSize;index++){
			CommonFileDTO cfile = fileList.get(index);
			InfoVo infoVo = bisInfoList.get(index);
			if(infoVo == null){
				continue;
			}
			
			infoVo.setFileMd5(cfile.getFileMD5());
		}
		this.fillUp(bisInfoList);
		
	}

	@Override
	public boolean fillUp(InfoVo bisInfo) {
		MongoUtil mongoUtil = null;
		try {
			mongoUtil = MongoUtil.getInstance();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		if(mongoUtil == null){
			return false;
		}
		Datastore ds = mongoUtil.getDS();
		if(ds == null){
			return false;
		}
		
		/*
		BisAttrDTO bisAttr = new BisAttrDTO();
		bisAttr.setCopyright(bisInfo.getCopyright());
		bisAttr.setOwner(bisInfo.getOwner());
		bisAttr.setPermission(bisInfo.getPermission());
		bisAttr.setSource(bisInfo.getSource());
		bisAttr.setTitle(bisInfo.getTitle());
		bisAttr.setUploader(bisInfo.getUploader());
		bisAttr.setTagList(bisInfo.getTagList());
		*/
		
		Query<CommonFileDTO> query = ds.createQuery(CommonFileDTO.class).field("fileMD5").equal(bisInfo.getFileMd5());
		UpdateOperations<CommonFileDTO> ops = ds.createUpdateOperations(CommonFileDTO.class)
//				.addAll("bisAttr.tagList",bisInfo.getTagList(),false)
				.set("bisAttr", bisInfo.getAttr());
		UpdateResults update = ds.update(query, ops);
		return true;
	}

	@Override
	public void createIndex(String fileMd5) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateIndex(String fileMd5) {
		// TODO Auto-generated method stub
		
	}

}
