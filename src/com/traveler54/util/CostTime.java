package com.traveler54.util;

/**
 * 
 * @author EX-WANGXIAOYU590
 *
 */
public class CostTime {

	private transient long start;
	
	public CostTime(){
		this.start();
	}
	
	public void start(){
		this.start = System.currentTimeMillis();
	}
	
	public long cost(){
		return System.currentTimeMillis() - start;
	}
	
}
