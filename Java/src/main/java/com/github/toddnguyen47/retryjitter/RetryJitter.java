package com.github.toddnguyen47.retryjitter;

import java.time.Instant;
import java.util.Random;

/**
 * RetryJitter - Ref: https://aws.amazon.com/blogs/architecture/exponential-backoff-and-jitter/
 */
public final class RetryJitter {

  private static final Random RANDOM = new Random(Instant.now().toEpochMilli());
  private static final int MIN_SLEEP_TIME_MILLIS = 50;

  private RetryJitter() {}

  public static void retry(
      final int retryTimes, final int timeoutMilliseconds, final RetryFunction retryFunction)
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

    for (; count <= retryTimes && keepRetrying; count++) {
      if (count > 0) {
        int maxSleep = (timeoutMillisInner << (count - 1)) + 1 - MIN_SLEEP_TIME_MILLIS;
        int randomSleepTime = RANDOM.nextInt(maxSleep) + MIN_SLEEP_TIME_MILLIS;
        // TODO: LOG HERE
        System.out.printf(
            "Current retry count: %d, Sleep for: %d millis\n", count, randomSleepTime);
        sleepFor(randomSleepTime);
      }
      boolean results = retryFunction.run();
      if (results) {
        keepRetrying = false;
      }
    }

    // Failure - max retry count reached
    if (keepRetrying) {
      String msg =
          String.format("retry count '%d' exceeds max retry count of '%d'", count, retryTimes);
      throw new RetryJitterException(msg);
    }
  }

  private static void sleepFor(final long millis) {
    try {
      Thread.sleep(millis);
    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  @FunctionalInterface
  public interface RetryFunction {
    boolean run();
  }
}
