package com.traveler54.common.server;

import com.traveler54.common.server.resp.Resp;


/**
 * 
 * @author EX-WANGXIAOYU590
 *
 */
public interface Handler {

	 public Resp handleRequest(NettyHttpRequest request);

}
