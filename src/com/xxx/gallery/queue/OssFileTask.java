package com.xxx.gallery.queue;

import java.util.ArrayList;
import java.util.List;

import com.xxx.gallery.vo.InfoVo;
import com.xxx.taskQueue.vo.BaseTask;

public class OssFileTask extends BaseTask {

	private List<InfoVo> bisInfoList;

	public OssFileTask() {
		super();
	}

	public OssFileTask(List<InfoVo> bisInfoList) {
		super();
		this.bisInfoList = bisInfoList;
		this.taskService = new OssFileTaskServiceImpl();
	}

	public OssFileTask(InfoVo infoVo) {
		super();
		this.bisInfoList = new ArrayList<InfoVo>();
		this.bisInfoList.add(infoVo);
		this.taskService = new OssFileTaskServiceImpl();
	}

	public List<InfoVo> getBisInfoList() {
		return bisInfoList;
	}

	public void setBisInfoList(List<InfoVo> bisInfoList) {
		this.bisInfoList = bisInfoList;
	}

}
