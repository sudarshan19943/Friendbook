package com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class EmailValidatorTest {
	
	@Test
	public void testEmailisEmpty() {
		String emailIsEmpty=null;
		assertFalse(EmailValidator.isValidEmailAddress(emailIsEmpty));
	}
	
	
	@Test
	public void testEmailWithNoDomain() {
		String emailWithNoDomain="smn.singh";
		assertFalse(EmailValidator.isValidEmailAddress(emailWithNoDomain));
	}
	
	@Test
	public void testEmailWithNoAtTheRateSymbol() {
		String emailWithNoAtTheRateSymbol="smn.singh.gmail.com";
		assertFalse(EmailValidator.isValidEmailAddress(emailWithNoAtTheRateSymbol));
	}
	
	@Test
	public void testEmailWithMissingPeriod() {
		String emailWithNoDot="smn.singh@gmailcom";
		assertFalse(EmailValidator.isValidEmailAddress(emailWithNoDot));
	}
	
	@Test
	public void testValidEmail() {
		String emailWithNoAtTheRateSymbol="smn.singh@gmail.com";
		assertTrue(EmailValidator.isValidEmailAddress(emailWithNoAtTheRateSymbol));
	}

	

}
