package com.macs.groupone.common;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
	
	private static Properties defaultProps = new Properties();
	  static {
	    try {
	        FileInputStream in = new FileInputStream("src/main/resources/application.properties");
	        defaultProps.load(in);
	        in.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	  }
	  public static String getProperty(String key) {
	    return defaultProps.getProperty(key);
	  }

}
