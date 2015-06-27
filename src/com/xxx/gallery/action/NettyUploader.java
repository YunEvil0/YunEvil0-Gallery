package com.xxx.gallery.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;

public class NettyUploader extends FileUpload {
	/** 
	 * 上传的内容流 
	 */
	private InputStream inputStream;

	public NettyUploader(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public NettyUploader(FileItemFactory fileItemFactory) {
		super(fileItemFactory);
	}

	@SuppressWarnings("unchecked")
	public List<FileItem> parseRequest(String encoding, String contentType, int contentLength) throws FileUploadException {
		NettyRequestContext nettyRequestContext = new NettyRequestContext(encoding, contentType, contentLength, inputStream);
		return parseRequest(nettyRequestContext);
	}

	public FileItemIterator getItemIterator(String encoding, String contentType, int contentLength)
		throws FileUploadException,IOException {
		this.setHeaderEncoding(encoding == null ? "UTF-8" : encoding);
		NettyRequestContext nettyRequestContext = new NettyRequestContext(encoding, contentType, contentLength, inputStream);
		return getItemIterator(nettyRequestContext);
	}
}