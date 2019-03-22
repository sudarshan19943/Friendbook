package com.macs.groupone.friendbookapplication;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages={"com.macs.groupone.friendbookapplication", "com.macs.groupone.friendbookapplication.controller","com.macs.groupone.friendbookapplication.model","com.macs.groupone.friendbookapplication.service","com.macs.groupone.friendbookapplication.dao"})
public class FriendBookApplication extends SpringBootServletInitializer {
	
	final static Logger logger = Logger.getLogger(FriendBookApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FriendBookApplication.class, args);
		PropertyConfigurator.configure("src/main/resources/log4j.properties");
		logger.trace("Friendbook Application has been started..");
		logger.trace("Testing heroku CI.again...");
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FriendBookApplication.class);
	}

	

}

