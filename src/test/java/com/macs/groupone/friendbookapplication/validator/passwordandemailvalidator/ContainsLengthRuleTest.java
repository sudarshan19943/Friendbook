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
public class ContainsLengthRuleTest {
	
	ContainsLengthRule containsLengthRule;
	
	@Before
	public void setUp() throws Exception {
		containsLengthRule=new ContainsLengthRule();
	}
	
	@After
	public void tearDown() {
		containsLengthRule = null;
	}

	@Test
	public void testPasswordNotContainsRequiredLength() {
		assertFalse(containsLengthRule.isCriteriaSatisfied("PAS"));

	}

	@Test
	public void testPasswordContainsRequiredLength() {
		assertTrue(containsLengthRule.isCriteriaSatisfied("PASSWORD09"));
	}
	
	

}
