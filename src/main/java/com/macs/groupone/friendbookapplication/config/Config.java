package com.macs.groupone.friendbookapplication.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;



public class Config {

	final static Logger logger = Logger.getLogger(Config.class);
	public static final String APPLICATION_PROPERTIES = "src/main/resources/application.properties";

	private static Properties defaultProps = new Properties();
	static {
		try {
			FileInputStream in = new FileInputStream(APPLICATION_PROPERTIES);
			defaultProps.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public static String getProperty(String key) {
		return defaultProps.getProperty(key);
	}

}
