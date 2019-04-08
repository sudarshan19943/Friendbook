package com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContainsNonAlphanumericRuleTest {
	

	ContainsNonAlphanumericRule containsNonAlphanumericRule;
	
	@Before
	public void setUp() throws Exception {
		containsNonAlphanumericRule=new ContainsNonAlphanumericRule();
	}
	
	@After
	public void tearDown() {
		containsNonAlphanumericRule = null;
	}
	
	@Test
	public void testPasswordContainesNonAplphaNumericOnlyAplphas() {
		assertTrue(containsNonAlphanumericRule.isCriteriaSatisfied("suman"));
	}
	
	@Test
	public void testPasswordContainesNonAplphaNumericOnlyNumbers() {
		assertTrue(containsNonAlphanumericRule.isCriteriaSatisfied("6317617"));
	}
	
	@Test
	public void testPasswordContainesAlphaNumeric() {
		assertTrue(containsNonAlphanumericRule.isCriteriaSatisfied("736172836AFBJFDJF"));
	}


	@Test
	public void testPasswordDoestestPasswordContainesNonAplphaNumeric() {
		assertFalse(containsNonAlphanumericRule.isCriteriaSatisfied("#$$#%$%$%"));
	}


}
