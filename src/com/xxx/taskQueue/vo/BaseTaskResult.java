package com.xxx.taskQueue.vo;

public abstract class BaseTaskResult {

	private boolean isSuccess;

	public BaseTaskResult() {
		super();
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

}
