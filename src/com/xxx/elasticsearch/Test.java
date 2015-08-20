package com.xxx.elasticsearch;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.alibaba.fastjson.JSON;

public class Test {

	public static void main(String[] args) {
		
		Client client = new TransportClient()
        .addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300));
		Map<String,Object> source = new HashMap<String, Object>();
		source.put("FileID", "2f111c1d844b024a1ece2437bee87566");
		source.put("FileSize", 11);
		IndexResponse indexResp = client.prepareIndex("t1", "t2","2f111c1d844b024a1ece2437bee87566").setSource(JSON.toJSONString(source)).execute().actionGet();
		System.out.println(JSON.toJSONString(indexResp));
	}
}
