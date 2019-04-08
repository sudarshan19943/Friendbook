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
public class ContainesLowerCaseRuleTest {

	ContainesLowerCaseRule containesLowerCaseRule;

	@Before
	public void setUp() throws Exception {
		containesLowerCaseRule = new ContainesLowerCaseRule();
	}
	@After
	public void tearDown() {
		containesLowerCaseRule = null;
	}

	@Test
	public void testPasswordContainsLoweCase() {
		assertTrue(containesLowerCaseRule.isCriteriaSatisfied("PassWord"));
	}

	@Test
	public void testPasswordDoesNotContainsLoweCase() {
		assertFalse(containesLowerCaseRule.isCriteriaSatisfied("PASSWORD"));

	}

}
