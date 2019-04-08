package com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class ContainsUpperCaseRuleTest {
	
	ContainsUpperCaseRule containsUpperCaseRule;

	@Before
	public void setUp() throws Exception {
		containsUpperCaseRule=new ContainsUpperCaseRule();
	}
	
	@After
	public void tearDown() {
		containsUpperCaseRule = null;
	}
	
	@Test
	public void tesContainsUpperCaseRule() {
		assertTrue(containsUpperCaseRule.isCriteriaSatisfied("passWOrd"));

	}

	@Test
	public void testDoesNotSatisfyContainsUpperCaseRule() {
		assertFalse(containsUpperCaseRule.isCriteriaSatisfied("password"));

	}

}
