package com.github.toddnguyen47.retryjitter;

import com.github.toddnguyen47.retryjitter.RetryJitter.RetryFunction;
import org.junit.Assert;
import org.junit.Test;

public class RetryJitterTest {

  @Test
  public void Test_GivenRetrySuccess_ThenNoExceptionIsThrown() {
    // -- ARRANGE --
    RetryFunctionTest rft = new RetryFunctionTest();
    // -- ACT --
    // -- ASSERT --
    try {
      RetryJitter.retry(3, 100, rft);
    } catch (final RetryJitterException e) {
      Assert.fail("no exceptions should have been thrown");
    }
  }

  @Test
  public void Test_GivenRetryFailureAtFirstButThenSucecss_ThenNoExceptionIsThrown() {
    // -- ARRANGE --
    RetryFunctionTest rft = new RetryFunctionTest();
    rft.code = "FFFP";
    // -- ACT --
    // -- ASSERT --
    try {
      RetryJitter.retry(3, -1, rft);
    } catch (final RetryJitterException e) {
      Assert.fail("no exceptions should have been thrown");
    }
  }

  @Test
  public void Test_GivenRetryFailure_ThenThrowException() {
    // -- ARRANGE --
    RetryFunctionTest rft = new RetryFunctionTest();
    rft.code = "FFFFFF";
    // -- ACT --
    // -- ASSERT --
    try {
      RetryJitter.retry(3, -1, rft);
      Assert.fail("exceptions should have been thrown");
    } catch (final RetryJitterException e) {
      // We pass! Do nothing
    }
  }

  private class RetryFunctionTest implements RetryFunction {

    /*
     * code - "FFFPPP", "F" for fail, "P" for pass
     */
    public String code = "";

    @Override
    public boolean run() {
      if (!code.isEmpty()) {
        char firstChar = code.charAt(0);
        code = code.substring(1);
        if (firstChar == 'F') {
          return false;
        }
      }
      return true;
    }
  }
}
