package com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContainsLengthRuleTest {
	
	ContainsLengthRule containsLengthRule;
	
	@Before
	public void setUp() throws Exception {
		containsLengthRule=new ContainsLengthRule();
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
