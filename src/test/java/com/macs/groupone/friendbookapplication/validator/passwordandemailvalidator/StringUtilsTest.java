package com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void tesStringIsNull() {
		boolean stringIsNull = StringUtils.isNullOrEmpty(null);
		assertTrue(stringIsNull);
	}

	@Test
	public void testStringIsEmpty() {
		boolean stringIsEmpty = StringUtils.isNullOrEmpty("");
		assertTrue(stringIsEmpty);
	}

	@Test
	public void testStringIsNotEmpty() {
		boolean stringIsNotEmpty = StringUtils.isNullOrEmpty("suman");
		assertFalse(stringIsNotEmpty);
	}

	@Test
	public void tesStringIsWhitespace() {
		boolean stringIsWhitespace = StringUtils.isNullOrWhiteSpace("    ");
		assertTrue(stringIsWhitespace);
	}

	@Test
	public void tesStringIsNotWhitespace() {
		boolean stringIsWhitespace = StringUtils.isNullOrWhiteSpace("suman");
		assertFalse(stringIsWhitespace);

	}

	@Test
	public void tesStringIsNullOrWhitespace() {
		boolean stringIsWhitespace = StringUtils.isNullOrWhiteSpace(null);
		assertTrue(stringIsWhitespace);
	}

	@Test
	public void tesStringIsNotNullorWhitespace() {
		boolean stringIsWhitespace = StringUtils.isNullOrWhiteSpace("suman");
		assertFalse(stringIsWhitespace);

	}

}
