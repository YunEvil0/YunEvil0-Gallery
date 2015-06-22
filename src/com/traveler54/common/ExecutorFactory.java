package com.traveler54.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 
 * @author EX-WANGXIAOYU590
 *
 */
public class ExecutorFactory {

	public static ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
	public static ExecutorService fixedExecutor = Executors.newFixedThreadPool(40);
	public static ExecutorService cachedExecutor = Executors.newCachedThreadPool();
	
}
