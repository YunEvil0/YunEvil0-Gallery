//package test;
//
//import java.io.IOException;
//
//import com.alisoft.xplatform.asf.cache.memcached.client.MemCachedClient;
//import com.alisoft.xplatform.asf.cache.memcached.client.SockIOPool;
//
//public class TestSession {
//	public static void main(String[] args) throws IOException {
//		
//		String[] serverlist = { "192.168.1.35:11211"};  
//		   
//        SockIOPool pool = SockIOPool.getInstance();  
//        pool.setServers(serverlist);  
//        pool.initialize();  
//        
//		MemCachedClient client = new MemCachedClient();
////		client.add("a", "a");
//		Object object = client.get("lcbj9hpo17nib2495fjalm8nt0");
//		System.out.println("");
//	}
//}
