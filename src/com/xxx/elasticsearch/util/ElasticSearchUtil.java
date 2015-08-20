package com.xxx.elasticsearch.util;

import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.xxx.common.Config;

public class ElasticSearchUtil {

	private static ElasticSearchUtil instance = null;
	private static Client client = null;
	
	public synchronized static ElasticSearchUtil getInstance() throws UnknownHostException{
		if (instance == null) {
			instance = new ElasticSearchUtil();
		}
		return instance;
	}
	
	private ElasticSearchUtil() throws UnknownHostException {
		initCtx();
	}

	private void initCtx() throws UnknownHostException {
		if(client == null){
			client = new TransportClient().addTransportAddress(new InetSocketTransportAddress(Config.get().get("elasticSearch.server"), 9300));
		}
	}
	
	public static Client getClient(){
		return client;
	}
	

}
