package com.github.toddnguyen47.testfileshelpers;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;

public final class MyAssertHelper {
  private MyAssertHelper() {}

  /**
   * Assert that the two strings are equal
   * @param expected
   * @param actual
   */
  public static void assertString(final String expected, final String actual) {
    MatcherAssert.assertThat(actual, CoreMatchers.equalTo(expected));
  }
}
