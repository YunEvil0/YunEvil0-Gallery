package com.traveler54.common.server;

import net.sf.json.JSONException;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;

import com.traveler54.common.server.resp.ErrorResp;
import com.traveler54.common.server.resp.Resp;
import com.traveler54.gallery.resp.ImageReadResp;
import com.traveler54.util.CostTime;
import com.traveler54.util.logging.ESLogger;
import com.traveler54.util.logging.Loggers;

/**
 * 
 * @author EX-WANGXIAOYU590
 *
 */
public class RestChannelHandler extends SimpleChannelHandler {
	
	private static ESLogger log = Loggers.getLogger(RestChannelHandler.class);
	private static final String CONTENT_TYPE = "application/json;charset=utf-8";
	private final PathTrie<Handler> getHandlers = new PathTrie<Handler>();
	private final PathTrie<Handler> postHandlers = new PathTrie<Handler>();
	private final PathTrie<Handler> putHandlers = new PathTrie<Handler>();
	private final PathTrie<Handler> deleteHandlers = new PathTrie<Handler>();
	private final PathTrie<Handler> optionsHandlers = new PathTrie<Handler>();
	
	public RestChannelHandler(){
	}

	public void messageReceived(ChannelHandlerContext ctx, final MessageEvent me) throws Exception {
		final HttpRequest hr = (HttpRequest) me.getMessage();
		CostTime cost = new CostTime();
		cost.start();
		NettyHttpRequest request = new NettyHttpRequest(hr);
		Channel channel = me.getChannel();
		final Handler handler = getHandler(request);
		if (handler == null) {
			ErrorResp resp = new ErrorResp("No handler found for uri ["+ request.uri() + "] and method [" + request.method() + "]",404);
			doHttpResp(request,resp, hr, channel);
		}else{
			try {
				Resp resp = handler.handleRequest(request);
				doHttpResp(request,resp, hr, channel);
			} catch (JSONException e){
				ErrorResp resp = new ErrorResp(e,400);
				doHttpResp(request,resp, hr, channel);
			} catch (Exception e) {
				ErrorResp resp = new ErrorResp(e);
				doHttpResp(request,resp, hr, channel);
			}
		}
		if(log.isInfoEnabled() && cost.cost()>100)
			log.warn("{} {} costTime:{}" ,hr.getUri(), hr.getHeaders(), cost.cost());
	}
	public void registerHandler(HttpMethod method, String path, Handler handler) {
		if (method == HttpMethod.GET) {
			getHandlers.insert(path, handler);
		} else if (method == HttpMethod.POST) {
			postHandlers.insert(path, handler);
		} else if (method == HttpMethod.PUT) {
			putHandlers.insert(path, handler);
		} else if (method == HttpMethod.DELETE) {
			deleteHandlers.insert(path, handler);
		}else if(method == HttpMethod.OPTIONS){
			optionsHandlers.insert(path, handler);
		} else {
			throw new RuntimeException("HttpMethod is not supported");
		}
	}
	
	private final void doHttpResp(NettyHttpRequest req,Resp response, final HttpRequest request,final Channel channel){
		if(response instanceof ImageReadResp){
			ImageReadResp imageResp = (ImageReadResp)response;
			doHttpImageResp(req,imageResp, request, channel, "image/jpg");
		}else{
			doHttpResp(req,response, request, channel, CONTENT_TYPE);
		}
	}
	
	private final void doHttpImageResp(NettyHttpRequest req,ImageReadResp response, final HttpRequest request,final Channel channel, String contentType){
		DefaultHttpResponse defaultResponse = new DefaultHttpResponse(request.getProtocolVersion(),HttpResponseStatus.valueOf(response.respCode));
		defaultResponse.setContent(ChannelBuffers.copiedBuffer(response.getData()));
		defaultResponse.setHeader("Content-Type", contentType);
		defaultResponse.setHeader("Content-Length", response.getData().length);
		
		boolean close = !HttpHeaders.isKeepAlive(request);
//		close = true;
		defaultResponse.setHeader(HttpHeaders.Names.CONNECTION,close ? HttpHeaders.Values.CLOSE : HttpHeaders.Values.KEEP_ALIVE);
		
		ChannelFuture cf = channel.write(defaultResponse);
		
		String remoteAddress = channel.getRemoteAddress().toString();
		String uri = request.getUri();
		String method = request.getMethod().getName();
		log.info("{},{},{}",remoteAddress,uri,method);
		if (close) {
			cf.addListener(ChannelFutureListener.CLOSE);
		}
	}
	
	private final void doHttpResp(NettyHttpRequest req,Resp response, final HttpRequest request,final Channel channel, String contentType){
		DefaultHttpResponse defaultResponse = new DefaultHttpResponse(request.getProtocolVersion(),HttpResponseStatus.valueOf(response.respCode));
		defaultResponse.setContent(response.toJson());
		defaultResponse.setHeader("Content-Type", contentType);
		defaultResponse.setHeader("Content-Length", defaultResponse.getContent().readableBytes());
		boolean close = !HttpHeaders.isKeepAlive(request);
//		close = true;
		defaultResponse.setHeader(HttpHeaders.Names.CONNECTION,close ? HttpHeaders.Values.CLOSE : HttpHeaders.Values.KEEP_ALIVE);
		
		ChannelFuture cf = channel.write(defaultResponse);
		
		String remoteAddress = channel.getRemoteAddress().toString();
		String uri = request.getUri();
		String method = request.getMethod().getName();
		log.info("{},{},{}",remoteAddress,uri,method);
		if (close) {
			cf.addListener(ChannelFutureListener.CLOSE);
		}
	}
	private Handler getHandler(NettyHttpRequest request) {
		String path = request.path();
		HttpMethod method = request.method();
		if (method == HttpMethod.GET) {
			return getHandlers.retrieve(path, request.params());
		} else if (method == HttpMethod.POST) {
			return postHandlers.retrieve(path, request.params());
		} else if (method == HttpMethod.PUT) {
			return putHandlers.retrieve(path, request.params());
		} else if (method == HttpMethod.DELETE) {
			return deleteHandlers.retrieve(path, request.params());
		} else if (method == HttpMethod.OPTIONS) {
			return optionsHandlers.retrieve(path, request.params());
		} else {
			return null;
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		if (log.isTraceEnabled())
			log.trace("Connection exceptionCaught:{}", e.getCause().toString());
		e.getChannel().close();
	}
}
