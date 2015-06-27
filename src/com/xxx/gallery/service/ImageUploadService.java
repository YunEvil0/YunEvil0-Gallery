package com.xxx.gallery.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadBase.FileUploadIOException;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferInputStream;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.alibaba.fastjson.JSON;
import com.xxx.common.Config;
import com.xxx.common.server.NettyHttpRequest;
import com.xxx.gallery.action.NettyUploader;
import com.xxx.gallery.dto.CommonFileDTO;
import com.xxx.gallery.exception.BisException;
import com.xxx.gallery.resp.ImageUploadResp;
import com.xxx.gallery.vo.UploadFileStrategyBaseVo;
import com.xxx.gallery.vo.UploadFileStrategyFactory;
import com.xxx.gallery.vo.UploadFileStrategyVo;
import com.xxx.mongo.MongoUtil;

public class ImageUploadService {
	
	
	public final static String ROOT_PATH=Config.get().get("file_root_path");
	
	public ImageUploadResp doAction(NettyHttpRequest nettyRequest){
		HttpRequest httpRequest=nettyRequest.getRequest();
		 
		if(!isMultipartContent(httpRequest)){
			return new ImageUploadResp("Content-Type must be "+FileUploadBase.MULTIPART_FORM_DATA+" or "+FileUploadBase.MULTIPART_MIXED, HttpResponseStatus.NOT_ACCEPTABLE.getCode());
		}
		
		HttpMethod method = httpRequest.getMethod();
		if(method != HttpMethod.POST){
			return new ImageUploadResp("HttpMethod is not support", HttpResponseStatus.METHOD_NOT_ALLOWED.getCode());
		}
		
		if(!httpRequest.isChunked()){
			return this.processImage(nettyRequest);
		}else{
//			log.error("Request is chunked!");
			return new ImageUploadResp("Request is chunked", HttpResponseStatus.BAD_REQUEST.getCode());
		}
	}
	
	private final boolean isMultipartContent(HttpRequest request) {
		if (HttpMethod.POST != request.getMethod()) {
			return false;
		}
		if (request.getHeaders("Content-Type") == null
				&& request.getHeaders("Content-Type").size() == 0) {
			return false;
		}
		String contentType = request.getHeaders("Content-Type").get(0);
		if (contentType == null) {
			return false;
		}
		if (contentType.toLowerCase().startsWith("multipart/")) {
			return true;
		}
		return false;
	}
	
	private ImageUploadResp processImage(NettyHttpRequest nettyRequest){
		HttpRequest httpRequest=nettyRequest.getRequest();
		String group = nettyRequest.param("group");
		
		UploadFileStrategyBaseVo strategyBaseVo = UploadFileStrategyFactory.getInstance().getStrategy(group);
		UploadFileStrategyVo strategyVo = JSON.parseObject(JSON.toJSONString(strategyBaseVo), UploadFileStrategyVo.class);
		
		InputStream ins = null;
		try {
			ChannelBuffer content = httpRequest.getContent();
			ins = new ChannelBufferInputStream(content);
			
			NettyUploader uploader = new NettyUploader(ins);
			uploader.setFileSizeMax(strategyVo.getFileSizeMax());//指定单个上传文件的最大尺寸  10M
			uploader.setSizeMax(strategyVo.getSizeMax());//指定一次上传多个文件的总尺寸  100M
			FileItemIterator iterator = uploader.getItemIterator("UTF-8",httpRequest.getHeader("Content-Type"), -1);
			List<CommonFileDTO> cfileList=new ArrayList<CommonFileDTO>();
			while (iterator.hasNext()) {
				
				FileItemStream fileItem = iterator.next();
				InputStream fins = fileItem.openStream();
				String fileName = fileItem.getName();
				if(fileName == null){
					cfileList.add(new CommonFileDTO("FILENAME_NULL"));
				}else if(fins == null){
					cfileList.add(new CommonFileDTO("FILESTREAM_NULL"));
				}else{
					strategyVo.checkStrategy(fileItem, fins);
					CommonFileDTO cfile = strategyVo.makeFile();
					String md5Code = cfile.getFileMD5();
					
					Datastore ds = MongoUtil.getInstance().getDS();
					Query<CommonFileDTO> query = ds.find(CommonFileDTO.class).field("fileMD5").equal(md5Code);
					CommonFileDTO existFile = query.get();
					if(existFile != null){
						cfile = existFile;
					}else{
						FileOutputStream fos=new FileOutputStream(new File(ROOT_PATH+cfile.getFilePath()));
						fos.write(strategyVo.getBytes());					
						fos.flush();
						fos.close();
						fos=null;
						ds.getCollection(CommonFileDTO.class).save(MongoUtil.getInstance().getMorphia().toDBObject(cfile));
					}
					
					cfileList.add(cfile);
				}
				if(fins != null){
					fins.close();
				}
			}
			ImageUploadResp resp= new ImageUploadResp(HttpResponseStatus.OK.getReasonPhrase(), HttpResponseStatus.OK.getCode());
			resp.setFileList(cfileList);
			return resp;
		}catch(FileUploadIOException ex){
			ex.printStackTrace();
			return new ImageUploadResp(HttpResponseStatus.BAD_REQUEST.getReasonPhrase(), HttpResponseStatus.BAD_REQUEST.getCode());
		}catch(BisException e){
			return new ImageUploadResp(e.getMessage(), HttpResponseStatus.BAD_REQUEST.getCode());
		}catch(Exception e){
			e.printStackTrace();
			return new ImageUploadResp(HttpResponseStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpResponseStatus.INTERNAL_SERVER_ERROR.getCode());
		}finally{
			try {
				if(ins != null){
					ins.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
