package com.xxx.common.server;

/**
 * 
 * @author EX-WANGXIAOYU590
 *
 */
public interface Server {
	
	public void init();

	public void start();

	public void stop();

	public String serverName();
}
