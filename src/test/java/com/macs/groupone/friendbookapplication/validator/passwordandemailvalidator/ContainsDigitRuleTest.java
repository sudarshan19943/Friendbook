package com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContainsDigitRuleTest {

	ContainsDigitRule containsDigitRule;

	@Before
	public void setUp() throws Exception {
		containsDigitRule = new ContainsDigitRule();
	}
	
	@After
	public void tearDown() {
		containsDigitRule = null;
	}

	@Test
	public void testPasswordNotContainsDigit() {
		assertFalse(containsDigitRule.isCriteriaSatisfied("PASSWORD"));

	}

	@Test
	public void testPasswordContainsDigit() {
		assertTrue(containsDigitRule.isCriteriaSatisfied("PASSWORD09"));

	}

}
