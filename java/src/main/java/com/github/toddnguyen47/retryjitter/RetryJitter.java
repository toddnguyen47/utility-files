package com.github.toddnguyen47.retryjitter;

import java.security.SecureRandom;
import java.time.Instant;

/**
 * RetryJitter - Ref:
 * https://aws.amazon.com/blogs/architecture/exponential-backoff-and-jitter/
 */
public final class RetryJitter {

  private static final SecureRandom RANDOM = new SecureRandom();
  private static final int MIN_SLEEP_TIME_MILLIS = 50;
  private static final int MAX_SLEEP_TIME_MILLIS = 20 * 1_000; // 20 seconds according to Amazon docs

  private RetryJitter() {
  }

  public static<T> T retry(
      final int retryTimes, final int timeoutMilliseconds, final RetryFunction<T> retryFunction)
      throws RetryJitterException {
    boolean keepRetrying = true;
    int timeoutMillisInner = timeoutMilliseconds;
    if (timeoutMillisInner < 0) {
      // Defaults to 100 milliseconds
      timeoutMillisInner = 100;
    }
    int count = 0;
    Instant now = Instant.now();
    RANDOM.setSeed(now.toEpochMilli());
    T t = null;
    RetryJitterException lastException = new RetryJitterException("");

    for (; count <= retryTimes && keepRetrying; count++) {
      if (count > 0) {
        int maxSleep = (timeoutMillisInner << (count - 1)) + 1 - MIN_SLEEP_TIME_MILLIS;
        int randomSleepTime = RANDOM.nextInt(maxSleep) + MIN_SLEEP_TIME_MILLIS;
        randomSleepTime = Math.min(randomSleepTime, MAX_SLEEP_TIME_MILLIS);
        // TODO: LOG HERE
        System.out.printf(
            "Current retry count: %d, Sleep for: %d millis\n", count, randomSleepTime);
        sleepFor(randomSleepTime);
      }
      try {
        t = retryFunction.run();
        keepRetrying = false;
      } catch (final RetryJitterException e) {
        keepRetrying = true;
        lastException = e;
      }
    }

    // Failure - max retry count reached
    if (keepRetrying) {
      String msg = String.format("retry count '%d' exceeds max retry count of '%d'", count, retryTimes);
      throw new RetryJitterException(msg, lastException);
    }
    return t;
  }

  public static void sleepFor(final long millis) {
    try {
      Thread.sleep(millis);
    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  @FunctionalInterface
  public interface RetryFunction<T> {
    /**
     * <p>Run the function that needs to retry.</p>
     * <p>If the function "passes", do nothing.</p>
     * <p>If the function "fails", throw a RetryJitterException.</p>
     */
    T run() throws RetryJitterException;
  }
}
