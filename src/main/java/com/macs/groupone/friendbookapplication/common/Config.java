package com.macs.groupone.friendbookapplication.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.macs.groupone.friendbookapplication.constants.Constants;

public class Config {
	
	private static Properties defaultProps = new Properties();
	  static {
	    try {
	        FileInputStream in = new FileInputStream(Constants.APPLICATION_PROPERTIES);
	        defaultProps.load(in);
	        in.close();
	    }catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	  }
	  public static String getProperty(String key) {
	    return defaultProps.getProperty(key);
	  }

}
