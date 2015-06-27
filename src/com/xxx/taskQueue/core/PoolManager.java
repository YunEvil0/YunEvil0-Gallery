package com.xxx.taskQueue.core;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.xxx.taskQueue.vo.BaseTask;

/**
 * 消息队列单例类
 * @author EX-WANGXIAOYU590
 *
 */
public class PoolManager {
	/** 阻塞队列  **/
	private BlockingQueue<BaseTask> queue=new ArrayBlockingQueue<BaseTask>(2000);
	/** 实例  **/
	private static PoolManager tpm=new PoolManager();
	
	public static int totalSize = 0;
	
	private PoolManager(){
		
	}
	
	public static PoolManager getInstance(){
		return tpm;
	}
	
	public boolean offer(BaseTask msg){
		try {
			queue.put(msg);
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	public BaseTask pull(){
		try {
			totalSize++;
			return this.queue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getQueueSize(){
		return this.queue.size();
	}
	
	public boolean isEmpty(){
		return this.queue.isEmpty();
	}
}
