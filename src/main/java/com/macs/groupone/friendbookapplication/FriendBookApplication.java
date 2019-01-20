package com.macs.groupone.friendbookapplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FriendBookApplication extends SpringBootServletInitializer {
	
	static Logger logger = LoggerFactory.getLogger(FriendBookApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FriendBookApplication.class, args);
		logger.trace("Frindbook Application has been started..");
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FriendBookApplication.class);
	}

	

}

