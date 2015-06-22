package com.traveler54.gallery.action;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.RequestContext;

public class NettyRequestContext implements RequestContext {  
    private String encoding;  
    private String contentType;  
    private long contentLength = -1;  
    /** 
     * 上传的内容流 
     */  
    private InputStream inputStream;  
    public NettyRequestContext(String encoding, String contentType,  
            long contentLength, InputStream inputStream) {  
        this.encoding = encoding;  
        this.contentType = contentType;  
        this.contentLength = contentLength;  
        this.inputStream = inputStream;  
    }  
    public String getCharacterEncoding() {  
        return encoding;  
    }  
    public String getContentType() {  
        return contentType;  
    }  
    public int getContentLength() {  
        return (int) contentLength;  
    }  
    public InputStream getInputStream() throws IOException {  
        return inputStream;  
    }  
    public String toString() {  
        return "ContentLength=" + this.getContentLength() + ", ContentType="  
                + this.getContentType();  
    }  
} 