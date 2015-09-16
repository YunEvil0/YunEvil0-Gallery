package com.xxx.gallery.service;

import java.util.List;

import com.xxx.gallery.dto.CommonFileDTO;
import com.xxx.gallery.vo.InfoVo;
import com.xxx.gallery.vo.TagVo;

public interface ImageBisService {

	public void fillUp(List<InfoVo> bisInfoList);

	public void fillUp(List<InfoVo> bisInfoList,List<CommonFileDTO> fileList);
	
	public boolean appendTag(String fileMd5,List<TagVo> tagList);
	
	public boolean fillUp(InfoVo bisInfo);
	
	public void createIndex(String fileMd5);
	
	public void updateIndex(String fileMd5);
	
	public void readExif(String fileMd5);
}
