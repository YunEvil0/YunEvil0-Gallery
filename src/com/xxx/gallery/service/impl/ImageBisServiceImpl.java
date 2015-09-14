package com.xxx.gallery.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.alibaba.fastjson.JSON;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.exif.GpsDirectory;
import com.xxx.common.Config;
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
		
		BisAttrDTO bisAttr = new BisAttrDTO();
		bisAttr.setCopyright(bisInfo.getCopyright());
		bisAttr.setOwner(bisInfo.getOwner());
		bisAttr.setPermission(bisInfo.getPermission());
		bisAttr.setSource(bisInfo.getSource());
		bisAttr.setTitle(bisInfo.getTitle());
		bisAttr.setUploader(bisInfo.getUploader());
		bisAttr.setTagList(bisInfo.getTagList());
		
		Query<CommonFileDTO> query = ds.createQuery(CommonFileDTO.class).field("fileMD5").equal(bisInfo.getFileMd5());
		UpdateOperations<CommonFileDTO> ops = ds.createUpdateOperations(CommonFileDTO.class)
//				.addAll("bisAttr.tagList",bisAttr.getTagList(),false)
				.set("bisAttr",bisInfo.getAttrJson());//用map好处就是不用修改java字段
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

	@Override
	public void readExif(String fileMd5) {
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
		
		Query<CommonFileDTO> query = ds.createQuery(CommonFileDTO.class).field("fileMD5").equal(fileMd5);
		CommonFileDTO cFileDTO = query.get();
		System.out.println(JSON.toJSONString(cFileDTO));
		String fileAbsPath = Config.get().get("file_root_path")+cFileDTO.getFilePath();
		
		Map<String,Object> exifMap = new HashMap<String,Object>();
		try {
			File file = new File(fileAbsPath);
			List<JpegSegmentMetadataReader> rList = new ArrayList<JpegSegmentMetadataReader>();
			rList.add(new ExifReader());
			
			Iterable<JpegSegmentMetadataReader> readers = rList;
			Metadata metadata = JpegMetadataReader.readMetadata(file, readers);
			
			List<String> tagLsit = Arrays.asList(Config.get().get("exif").split(","));
			
			for (Directory directory : metadata.getDirectories()) {
				for (Tag tag : directory.getTags()) {
					if (tagLsit.contains(tag.getTagName())) {
						exifMap.put(tag.getTagName(), tag.getDescription());
					}
				}
			}
			
			// Read all metadata from the image
            metadata = ImageMetadataReader.readMetadata(file);
            // See whether it has GPS data
            Collection<GpsDirectory> gpsDirectories = metadata.getDirectoriesOfType(GpsDirectory.class);
            if (gpsDirectories != null){
            	for (GpsDirectory gpsDirectory : gpsDirectories) {
            		// Try to read out the location, making sure it's non-zero
            		GeoLocation geoLocation = gpsDirectory.getGeoLocation();
            		if (geoLocation != null && !geoLocation.isZero()) {
            			// Add to our collection for use below
            			exifMap.put("latitude",geoLocation.getLatitude());
            			exifMap.put("longitude",geoLocation.getLongitude());
            			break;
            		}
            	}
            }
		} catch (JpegProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ImageProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UpdateOperations<CommonFileDTO> ops = ds.createUpdateOperations(CommonFileDTO.class).disableValidation()
				.set("fileAttr.exif", exifMap);
		UpdateResults update = ds.update(query, ops);
	}
	

}
