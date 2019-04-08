package com.macs.groupone.friendbookapplication.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ApplicationConfigPropertyConfigurator {
	
	private ApplicationConfigPropertyConfigurator()
	{
		
	}

	final static Logger logger = Logger.getLogger(ApplicationConfigPropertyConfigurator.class);
	private static final String APPLICATION_PROPERTIES = "src/main/resources/application.properties";

	private static Properties applicationProps = new Properties();
	static {
		try {
			FileInputStream in = new FileInputStream(APPLICATION_PROPERTIES);
			applicationProps.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return applicationProps.getProperty(key);
	}

}
