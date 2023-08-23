package com.github.toddnguyen47.datetimeutils;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DateTimeUtils that only uses built-in `java.time` libraries
 */
public final class DateTimeUtils {

  public static final DateTimeFormatter DT_ISO8601_MILLIS =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX").withZone(ZoneOffset.UTC);
  public static final DateTimeFormatter DT_ISO8601_NO_MILLIS =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX").withZone(ZoneOffset.UTC);
  public static final DateTimeFormatter DT_ISO8601_DATE_ONLY =
      DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneOffset.UTC);

  private DateTimeUtils() {}

  public static Instant nowUTC() {
    return Instant.now(Clock.system(ZoneOffset.UTC));
  }

  public static ZonedDateTime nowUTCDateTime() {
    Instant now = Instant.now();
    return now.atZone(ZoneOffset.UTC);
  }
}
