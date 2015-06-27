package com.xxx.common;

/**
 *  知道明确的错误原因的异常
 */
public class FanliException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public int errorCode = 502;
	public FanliException(String message) {
		super(message);
	}

	public FanliException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public FanliException(String message,int errcode) {
		super(message);
		this.errorCode=errcode;
	}

	public FanliException(String message, Throwable cause,int errcode) {
		super(message, cause);
	    this.errorCode=errcode;
	}
}
