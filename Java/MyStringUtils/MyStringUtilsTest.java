package com.aeg.schedule.schedule.event.util;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import org.hamcrest.Matchers;
import org.junit.Test;

public class MyStringUtilsTest {

	private static final String LOREM_IPSUM =
			"      Lorem ipsum dolor sit amet, consectetur adipiscing elit, \n sed \tdo\t eiusmod tempor incididunt ut labore "
			+ "et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi "
			+ "ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse "
			+ "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, "
			+ "sunt in culpa qui officia deserunt mollit anim id est laborum.";

	@Test
	public void test_GivenNull_When_ThenNoWhitespaceIsReturned() {
		final String actualString = MyStringUtils.getAbbrevStringNoWhitespace(null);

		assertNull(actualString);
	}

	@Test
	public void test_GivenWhitespaceStringLessThan100_When_ThenNoWhitespaceIsReturned() {
		final String inputStr = LOREM_IPSUM.substring(0, 97);
		final String actualString = MyStringUtils.getAbbrevStringNoWhitespace(inputStr);

		final String expectedString =
				"Loremipsumdolorsitamet,consecteturadipiscingelit,seddoeiusmodtemporincididu";
		assertThat(actualString, Matchers.equalTo(expectedString));
	}

	@Test
	public void test_GivenWhitespaceStringGreaterThan100_When_ThenNoWhitespaceIsReturned() {
		final String inputStr = LOREM_IPSUM.substring(0, 200);
		final String actualString = MyStringUtils.getAbbrevStringNoWhitespace(inputStr);

		final String expectedString =
				"Loremipsumdolorsitamet,consecteturadipiscingelit,seddoeiusmodtemporincididuntutlaboreetdoloremagn...";
		assertThat(actualString, Matchers.equalTo(expectedString));
	}
}
