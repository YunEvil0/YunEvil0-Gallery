package com.traveler54.gallery.vo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.io.IOUtils;
import org.apache.http.impl.cookie.DateUtils;

import com.traveler54.gallery.exception.BisException;
import com.traveler54.gallery.service.ImageUploadService;
import com.traveler54.util.ImageUtil;

public class UploadFileStrategyVo extends UploadFileStrategyBaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7949586744901114717L;

	private boolean isImage;
	private byte[] bytes;
	private String extName;
	private InputStream is;
	private BufferedImage bi;

	public UploadFileStrategyVo() {

	}

	public void checkStrategy(FileItemStream fileItem, InputStream fins)
			throws BisException, IOException {
		// 1.file ext name check
		ImageUtil imageUtil = new ImageUtil();

		this.extName = imageUtil.getFileExtName(fileItem.getName());

		if (!imageUtil.isAvailableExtName(this.getSuffix(), this.extName)) {
			throw new BisException("FILE EXT NAME IS NOT " + this.getSuffix());
		}
		// 2.file strean head check
		this.bytes = IOUtils.toByteArray(fins);

		if (!imageUtil
				.isAvailableFileType(this.bytes, this.getFileTypePreFix())) {
			throw new BisException("FILE FORMAT IS NOT "
					+ this.getFileTypePreFix());
		}

		// 3. file type check
		this.is = new ByteArrayInputStream(this.bytes);
		this.bi = imageUtil.isImage(this.is);
		
		if (this.bi != null) {
			this.isImage = true;
		}
		
		if (this.getFileType() != null) {
			switch (this.getFileType()) {
			case "images":
				throw new BisException("FILE IS NOT IMAGE");
			case "file":
				break;
			}
		}

	}

	public CommonFile makeFile() {
		String fileName = Long.toHexString(System.currentTimeMillis()) + "." + this.extName;
		
		
		StringBuffer filePathSb = new StringBuffer();
		if(this.isImage){
			filePathSb.append("images");
		}else{
			filePathSb.append(this.extName);
		}
		
		filePathSb.append(File.separator).append(DateUtils.formatDate(new Date(), "yyyyMM")).append(File.separator);
		String filePath = filePathSb.toString();
		File folder = new File(ImageUploadService.ROOT_PATH+filePath);
		if(!folder.exists()){
			folder.mkdirs();
		}
		
		filePathSb.append(this.getFilePrefix() == null ? "" : this.getFilePrefix())
		.append(fileName);
		filePath = filePathSb.toString();
		
		ImageUtil imageUtil = new ImageUtil();
		if (this.isImage) {
			ImageFile cfile = new ImageFile();
			cfile.height = imageUtil.getImageHeight(this.bi);
			cfile.width = imageUtil.getImageWidth(this.bi);

			cfile.setFileMD5(DigestUtils.md5Hex(this.bytes));
			cfile.setMsg("SUCCESS");
			cfile.setOssName(fileName);
			cfile.setSize(this.bytes.length);
			cfile.setFilePath(filePath);
			return cfile;
		} else {
			CommonFile cfile = new CommonFile();
			cfile.setFileMD5(DigestUtils.md5Hex(this.bytes));
			cfile.setMsg("SUCCESS");
			cfile.setOssName(fileName);
			cfile.setSize(this.bytes.length);
			cfile.setFilePath(filePath);
			return cfile;
		}
	}

	public boolean isImage() {
		return isImage;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public String getExtName() {
		return extName;
	}

}
