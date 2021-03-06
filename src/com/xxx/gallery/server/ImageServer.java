package com.xxx.gallery.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelUpstreamHandler;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.http.HttpChunkAggregator;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;
import org.jboss.netty.handler.stream.ChunkedWriteHandler;

import com.xxx.common.Config;
import com.xxx.common.server.BaseNioServer;
import com.xxx.common.server.RestChannelHandler;
import com.xxx.common.server.ServerAddress;
import com.xxx.gallery.handler.BisHandler;
import com.xxx.gallery.handler.ImageReadHandler;
import com.xxx.gallery.handler.UploadHandler;
import com.xxx.gallery.vo.UploadFileStrategyFactory;
import com.xxx.taskQueue.core.PoolPullThread;

public class ImageServer extends BaseNioServer {
	
	private RestChannelHandler restHandler;
	
	private void registerHandler() {
		restHandler = new RestChannelHandler();
		UploadHandler uploadHandler=new UploadHandler();
		restHandler.registerHandler(HttpMethod.OPTIONS, "galleryApi/imageUploadApi/{method_name}", uploadHandler);
		restHandler.registerHandler(HttpMethod.POST, "galleryApi/imageUploadApi/{method_name}", uploadHandler);
		
		ImageReadHandler readHandler = new ImageReadHandler();
		restHandler.registerHandler(HttpMethod.OPTIONS, "galleryApi/images/{method_name}", readHandler);
		restHandler.registerHandler(HttpMethod.GET, "galleryApi/images/{method_name}", readHandler);

		BisHandler bisHandler = new BisHandler();
		restHandler.registerHandler(HttpMethod.OPTIONS, "galleryApi/bis/{method_name}", bisHandler);
		restHandler.registerHandler(HttpMethod.GET, "galleryApi/bis/{method_name}", bisHandler);
		restHandler.registerHandler(HttpMethod.POST, "galleryApi/bis/{method_name}", bisHandler);
		
	}
	
	@Override
	protected ChannelPipelineFactory getChannelPipelineFactory() {
		return new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				// Create a default pipeline implementation.
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("decoder", new HttpRequestDecoder());
				pipeline.addLast("encoder", new HttpResponseEncoder());
				pipeline.addLast("aggregator", new HttpChunkAggregator(104857600));
				pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
				pipeline.addLast("query", restHandler);
				return pipeline;
			}
		};
	}

	@Override
	protected int defaultPort() {
		return 9101;
	}

	@Override
	public String serverName() {
		return "datapush-api-server";
	}

	@Override
	public void init() {
		this.registerHandler();
		super.init();
	}

	@Override
	public void start() {
		super.start();
	}

	@Override
	protected ServerAddress getServerAddress() {
		String listen = Config.get().get("server.listen");
		String address = Config.get().get("server.listen.address");
		String port = Config.get().get("server.listen.port");
		if (listen != null) {
			return new ServerAddress(listen);
		}
		return new ServerAddress(address,
				(port != null ? Integer.parseInt(port) : defaultPort()));
	}

	@Override
	protected ChannelUpstreamHandler finalChannelUpstreamHandler() {
		return null;
	}
	
	public static void main(String[] args) throws FileNotFoundException,IOException {
		String location = null;
		switch(System.getProperty("os.name").toLowerCase()){
		case "mac os x":
			location = "conf/fileStrategy.json";
			break;
		case "windows":
			location = "conf/fileStrategy.json";
			break;
		default:
			location = "../conf/fileStrategy.json";
		}
		BufferedReader br = new BufferedReader(new FileReader(location));
		String line = null;
		StringBuffer content = new StringBuffer();
		while((line = br.readLine()) != null){
			content.append(line);
		}
		br.close();
		br = null;
		ImageServer o = new ImageServer();
		o.init();
		o.start();
		
		UploadFileStrategyFactory.getInstance().addStrategy(content.toString());

		for(int i=0;i<20;i++){
        	Thread poolPullThread = new Thread(new PoolPullThread(), "DT"+i);
        	poolPullThread.start();
        }
		
	}

}
