package com.xxx.elasticsearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class Test {

	public static void main(String[] args) {
		Settings settings = ImmutableSettings.settingsBuilder()
		        .put("cluster.name", "JAVA_TEST").build();
		Client client = new TransportClient()
        .addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300));
	}
}
