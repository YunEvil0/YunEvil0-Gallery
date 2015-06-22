package com.traveler54.mongo;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.traveler54.common.Config;

public class MongoUtil {

	private static MongoUtil instance = null;
	private static Datastore ds = null;
	private static Morphia morphia = null;

	public synchronized static MongoUtil getInstance() throws UnknownHostException{
		if (instance == null) {
			instance = new MongoUtil();
		}
		return instance;
	}
	
	public Datastore getDS(){
		return ds;
	}
	
	public Morphia getMorphia(){
		return morphia;
	} 

	private MongoUtil() throws UnknownHostException {
		initCtx();
	}

	private void initCtx() throws UnknownHostException {
		if(morphia == null){
			morphia = new Morphia();
		}
		if (ds == null) {

			MongoCredential credit = MongoCredential.createCredential("xiaoyu", "oss", "Autobot_0317".toCharArray());
			ServerAddress sa= new ServerAddress(Config.get().get("mongoServer"));
			MongoClient client = new MongoClient(sa,Arrays.asList(credit));
			ds = morphia.createDatastore(client, "oss");
		}
	}

}
