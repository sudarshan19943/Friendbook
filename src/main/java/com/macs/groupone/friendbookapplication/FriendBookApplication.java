package com.macs.groupone.friendbookapplication;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class},scanBasePackages={"com.macs.groupone.friendbookapplication", "com.macs.groupone.friendbookapplication.controller","com.macs.groupone.friendbookapplication.model","com.macs.groupone.friendbookapplication.service","com.macs.groupone.friendbookapplication.dao","com.macs.groupone.friendbookapplication.validator","com.macs.groupone.friendbookapplication.validator.passwordvalidator","com.macs.groupone.friendbookapplication.validator.emailvalidator"})
public class FriendBookApplication extends SpringBootServletInitializer {
	
	final static Logger logger = Logger.getLogger(FriendBookApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FriendBookApplication.class, args);
		PropertyConfigurator.configure("src/main/resources/log4j.properties");
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FriendBookApplication.class);
	}

	

}

