package testutils;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;

public final class MyAssertHelper {
    private MyAssertHelper() {}

    /**
     * Assert that the two strings are equal
     * @param expected
     * @param actual
     */
    public static void assertString(final String expected, final String actual) {
        Assert.assertThat(actual, CoreMatchers.equalTo(expected));
    }
}
