package com.xxx.common.server;

import com.xxx.common.server.resp.Resp;


/**
 * 
 * @author EX-WANGXIAOYU590
 *
 */
public interface Handler {

	 public Resp handleRequest(NettyHttpRequest request);

}
