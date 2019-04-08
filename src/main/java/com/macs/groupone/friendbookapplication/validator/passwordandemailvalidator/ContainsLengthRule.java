package com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator;

import org.apache.log4j.Logger;

import com.macs.groupone.friendbookapplication.config.ValidationPropertyConfigurator;

public class ContainsLengthRule implements PasswordRule {
	
	final static Logger logger = Logger.getLogger(ContainsLengthRule.class);
	private static final String MIN_LENGTH=ValidationPropertyConfigurator.getProperty("minSize");
	private static final String MAX_LENGTH=ValidationPropertyConfigurator.getProperty("maxSize");
	
    @Override
    public boolean isCriteriaSatisfied(String password) {
    	try
    	{
         return password.length() >= Integer.parseInt(MIN_LENGTH) && password.length() <= Integer.parseInt(MAX_LENGTH);
    	}catch(NumberFormatException e)
    	{
    		logger.error("could not parse  password size Min and Max length : " +MIN_LENGTH+"," +MAX_LENGTH );
    		e.printStackTrace();
    	}
		return false;
    }
}
