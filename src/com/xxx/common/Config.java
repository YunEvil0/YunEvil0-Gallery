package com.xxx.common;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Config
{
	public static Logger log = LoggerFactory.getLogger(Config.class);
	public static String getConfigDir(){
		String location = null;
		switch(System.getProperty("os.name").toLowerCase()){
		case "mac os x":
			location = "/conf/";
			break;
		case "windows":
			location = "/conf/";
			break;
		default:
			location = "/../conf/";
		}
		
    	String configDir = null;
    	String userDir = System.getProperty("user.dir");
    	configDir = userDir + location;
    	log.info(configDir);
    	return configDir;
    }
	public static String getConfigPath(){
    	return getConfigDir()+"/config.properties";
    }
    private static final Config CONFIG = new Config()
    {
        Properties properties = new Properties();
        private final File cf = new File(getConfigPath());
    	long time;
		{
	        try{
	            if (!cf.exists()){
	                cf.getParentFile().mkdirs();
	                cf.createNewFile();
	            }
	            //当进程关闭，如果properties有修改，则写入文件
	            properties.load(new java.io.FileInputStream(cf));
	            log.info("loading config from:" + cf.getAbsolutePath());
	            time = cf.lastModified();
	            //检测配置文件是否被修改，自动reload
	            Runnable cmd = new Runnable(){
	    			@Override
	    			public void run() {
	    				long newlmd = cf.lastModified();
                        if (newlmd > time){
                            time = newlmd;
                            log.info("Config file {} is modified,reloading ...", cf.getAbsolutePath());
                            try{
                            	properties.load(new java.io.FileInputStream(cf));
                            }catch (IOException e){
                                log.error("Error while loading config file:{}", cf.getAbsolutePath());
                            }
                        }
	    			}
	    		};
	    		ExecutorFactory.scheduledExecutor.scheduleAtFixedRate(cmd, 0, 1, TimeUnit.MINUTES);
	            
	        }catch (IOException ex){
	            log.warn("cannot create log file", ex);
	        }
		}

        public String get(String key) {
            return properties.getProperty(key);
        }
        
		@Override
		public int refresh() {
			long newlmd = cf.lastModified();
            if (newlmd > time){
                time = newlmd;
                log.info("Config file {} is modified,reloading ...", cf.getAbsolutePath());
                try{
                	properties.load(new java.io.FileInputStream(cf));
                	return 1;//loas success;
                }catch (IOException e){
                    log.error("Error while loading config file:{}", cf.getAbsolutePath());
                    return 2;//excption;
                }
            }else{
            	return 0;//file has not benn changed;
            }
		}

		@Override
		public long getLong(String key) {
			return java.lang.Long.parseLong(Config.get().get(key));
		}

		@Override
		public int getInt(String key) {
			return java.lang.Integer.valueOf(Config.get().get(key));
		}

    };

    private Config(){
    }
    public static final Config get(){
    	return CONFIG;
    }
    abstract public String get(String key);
    abstract public long getLong(String key);
    abstract public int getInt(String key);
    abstract public int refresh();

}
