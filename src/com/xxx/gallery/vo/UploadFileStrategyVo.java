package com.xxx.gallery.vo;

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

import com.xxx.gallery.constant.GalleryConstant;
import com.xxx.gallery.dto.CommonFileDTO;
import com.xxx.gallery.dto.FileAttrCommonDTO;
import com.xxx.gallery.dto.FileAttrImageDTO;
import com.xxx.gallery.exception.BisException;
import com.xxx.gallery.service.ImageUploadService;
import com.xxx.util.ImageUtil;

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
	private String filepath;

	public UploadFileStrategyVo() {

	}

	public void checkStrategy(FileItemStream fileItem, InputStream fins) throws BisException{
		// 1.file ext name check
		ImageUtil imageUtil = new ImageUtil();

		this.extName = imageUtil.getFileExtName(fileItem.getName());

		if (!imageUtil.isAvailableExtName(this.getSuffix(), this.extName)) {
//			throw new BisException("FILE_EXT_NAME IS NOT " + this.getSuffix());
			throw new BisException("FILE_EXT_NAME");
		}
		// 2.file strean head check
		try {
			this.bytes = IOUtils.toByteArray(fins);
		} catch (IOException e) {
			throw new BisException("FILE_SIZE");
		}

		if (!imageUtil
				.isAvailableFileType(this.bytes, this.getFileTypePreFix())) {
//			throw new BisException("FILE_FORMAT IS NOT "+ this.getFileTypePreFix());
			throw new BisException("FILE_FORMAT");
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
				throw new BisException("FILE_IS_NOT_IMAGE");
			case "file":
				break;
			}
		}

	}

	public CommonFileDTO makeFile() {
		String fileName = Long.toHexString(System.currentTimeMillis()) + "."+ this.extName;

		StringBuffer filePathSb = new StringBuffer();
		if (this.isImage) {
			filePathSb.append("images");
		} else {
			filePathSb.append(this.extName);
		}
		if (filepath == null) {
			filePathSb.append(File.separator)
					.append(DateUtils.formatDate(new Date(), "yyyyMM"))
					.append(File.separator);
		} else {
			filePathSb.append(File.separator).append(filepath)
					.append(File.separator);
		}
		String filePath = filePathSb.toString();
		File folder = new File(ImageUploadService.ROOT_PATH + filePath);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		filePathSb.append(this.getFilePrefix() == null ? "" : this.getFilePrefix())
				.append(fileName);
		filePath = filePathSb.toString();

		ImageUtil imageUtil = new ImageUtil();
		if (this.isImage) {

			CommonFileDTO cfile = new CommonFileDTO();

			FileAttrImageDTO fileAttr = new FileAttrImageDTO();
			fileAttr.setHeight(imageUtil.getImageHeight(this.bi));
			fileAttr.setWidth(imageUtil.getImageWidth(this.bi));
			fileAttr.setApplicationType(GalleryConstant.FILE_APPLICATION_TYPE.IMAGE
					.toString());
			fileAttr.setSuffix(this.extName.toUpperCase());
			fileAttr.setSize(this.bytes.length);

			cfile.setFileAttr(fileAttr);
			cfile.setFileMD5(DigestUtils.md5Hex(this.bytes));
			cfile.setMsg("SUCCESS");
			cfile.setOssName(fileName);
			cfile.setFilePath(filePath);

			return cfile;
		} else {
			CommonFileDTO cfile = new CommonFileDTO();
			FileAttrCommonDTO fileAttr = new FileAttrCommonDTO();
			fileAttr.setApplicationType(GalleryConstant.FILE_APPLICATION_TYPE.DOCUMENT
					.toString());
			fileAttr.setSuffix(this.extName.toUpperCase());
			fileAttr.setSize(this.bytes.length);

			cfile.setFileMD5(DigestUtils.md5Hex(this.bytes));
			cfile.setMsg("SUCCESS");
			cfile.setOssName(fileName);
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

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

}
