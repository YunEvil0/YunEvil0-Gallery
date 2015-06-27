package com.xxx.taskQueue.core;

import com.xxx.taskQueue.vo.BaseTask;

/**
 * 从队列中取出消息发送
 * 
 * @author EX-WANGXIAOYU590
 * 
 */
public class PoolPullThread implements Runnable {

	public PoolPullThread() {
	}

	public void run() {
		while (true) {
			if (!PoolManager.getInstance().isEmpty()) {
				 System.out.println("=================================queue pull message===========================");
				try {
					BaseTask task = PoolManager.getInstance().pull();
					task.getTaskService().doTask(task);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				System.out.println("queue is empty");
			}

		}
	}

}
