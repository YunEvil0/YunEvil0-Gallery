package test;

import com.xxx.util.logging.ESLogger;
import com.xxx.util.logging.Loggers;

public class TestLog {
	private static ESLogger log = Loggers.getLogger(TestLog.class);

	public static void main(String[] args) {
		log.error("XXX:{},{}",1,3);
	}
}
