package com.xxx.taskQueue.core;

import com.xxx.gallery.queue.impl.ExifTaskServiceImpl;
import com.xxx.taskQueue.vo.BaseTask;
import com.xxx.util.logging.ESLogger;
import com.xxx.util.logging.Loggers;

/**
 * 从队列中取出消息发送
 * 
 * @author EX-WANGXIAOYU590
 * 
 */
public class PoolPullThread implements Runnable {
	
	private static ESLogger log = Loggers.getLogger(PoolPullThread.class);

	public PoolPullThread() {
	}

	public void run() {
		while (true) {
			if (!PoolManager.getInstance().isEmpty()) {
				BaseTask task = null;
				try {
					task = PoolManager.getInstance().pull();
					if(task !=null){
						log.info("START:{}",task);
						task.getTaskService().doTask(task);
						log.info("END:{}",task.getTaskId());
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.error("msg:{},taskId:{}",e.getMessage(), task==null?0:task.getTaskId());
				}
			} else {
				try {
					Thread.sleep(1000*10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
