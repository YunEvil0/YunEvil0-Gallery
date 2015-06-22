package com.traveler54.gallery.service;

import java.util.List;

import com.traveler54.gallery.vo.CommonFile;
import com.traveler54.gallery.vo.ImageBisInfoVo;

public interface ImageBisService {

	public void fillUp(List<ImageBisInfoVo> bisInfoList);

	public void fillUp(List<ImageBisInfoVo> bisInfoList,List<CommonFile> fileList);
	
	public void fillUp(ImageBisInfoVo bisInfo);
}
