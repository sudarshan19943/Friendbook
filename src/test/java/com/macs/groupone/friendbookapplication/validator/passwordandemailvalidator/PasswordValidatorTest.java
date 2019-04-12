package com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PasswordValidatorTest {
	
	private static final Logger log = LoggerFactory.getLogger(PasswordValidatorTest.class);
	public static final String VALIDATION_PROPERTIES = "src/main/resources/validation.properties";
	public static final String PASSWORD_POLICY_NOT_SATISFIED="Password Policy is not satisfied.";
	
	
	@Test
	public void tesValidatePasswordPolicy() {
		String passwordSatisfied=PasswordValidator.validatePasswordPolicy("passWOrd");
		assertTrue(null==passwordSatisfied);
		String passwordSatisfiedTest1=PasswordValidator.validatePasswordPolicy("password");
		assertEquals(defaultProps.getProperty(passwordSatisfiedTest1),PASSWORD_POLICY_NOT_SATISFIED);
		String passwordSatisfiedTest2=PasswordValidator.validatePasswordPolicy("PASSWORD");
		assertEquals(defaultProps.getProperty(passwordSatisfiedTest2),PASSWORD_POLICY_NOT_SATISFIED);
		String passwordSatisfiedTest3=PasswordValidator.validatePasswordPolicy("PASSWORD");
		assertEquals(defaultProps.getProperty(passwordSatisfiedTest3),PASSWORD_POLICY_NOT_SATISFIED);
	}
	
	
	private static Properties defaultProps = new Properties();
	  static {
	    try {
	        FileInputStream in = new FileInputStream(VALIDATION_PROPERTIES);
	        defaultProps.load(in);
	        in.close();
	    }catch (FileNotFoundException e) {
	    	log.error(e.getMessage());
	    } 
	    catch (IOException e) {
	    	log.error(e.getMessage());
	    }
	  }


	

}
