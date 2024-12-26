package com.github.toddnguyen47.datetimeutils;

import java.time.Instant;
import java.time.ZonedDateTime;
import org.junit.Assert;
import org.junit.Test;

public class DateTimeUtilsTest {

  @Test
  public void test_GivenFormatter_ThenFormatCorrectly() throws InterruptedException {
    Instant now = DateTimeUtils.nowUTC();
    Thread.sleep(1_500);
    ZonedDateTime nowZdt = DateTimeUtils.nowUTCDateTime();

    long nowLong = now.toEpochMilli();
    long nowZdtLong = nowZdt.toEpochSecond() * 1_000;
    Assert.assertTrue("should be less than", nowLong < nowZdtLong);
    System.out.println(nowZdt.format(DateTimeUtils.DT_ISO8601_DATE_ONLY));
    System.out.println(nowZdt.format(DateTimeUtils.DT_ISO8601_MILLIS));
    System.out.println(nowZdt.format(DateTimeUtils.DT_ISO8601_NO_MILLIS));
  }
}
