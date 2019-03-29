package com.macs.groupone.friendbookapplication.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.macs.groupone.friendbookapplication.service.AvatarService;


public class Config {
	
	private static final Logger log = LoggerFactory.getLogger(Config.class);
	public static final String APPLICATION_PROPERTIES = "src/main/resources/application.properties";
	
	
	
	private static Properties defaultProps = new Properties();
	  static {
	    try {
	        FileInputStream in = new FileInputStream(APPLICATION_PROPERTIES);
	        defaultProps.load(in);
	        in.close();
	    }catch (FileNotFoundException e) {
	    	log.error(e.getMessage());
	    } 
	    catch (IOException e) {
	    	log.error(e.getMessage());
	    }
	  }
	  public static String getProperty(String key) {
	    return defaultProps.getProperty(key);
	  }

}

