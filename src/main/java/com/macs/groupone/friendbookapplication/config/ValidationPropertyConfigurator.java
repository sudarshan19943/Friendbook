package com.macs.groupone.friendbookapplication.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ValidationPropertyConfigurator {
	
	private ValidationPropertyConfigurator()
	{
		
	}
	
	final static Logger logger = Logger.getLogger(ValidationPropertyConfigurator.class);
	private static final String VALIDATION_PROPERTIES = "src/main/resources/validation.properties";

	private static Properties validationProps = new Properties();
	static {
		try {
			FileInputStream in = new FileInputStream(VALIDATION_PROPERTIES);
			validationProps.load(in);
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
		return validationProps.getProperty(key);
	}

}
