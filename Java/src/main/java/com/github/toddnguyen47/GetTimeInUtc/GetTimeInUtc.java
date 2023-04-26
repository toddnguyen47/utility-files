package com.github.toddnguyen47.gettimeinutc;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public final class GetTimeInUtc {
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ssX";
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    private GetTimeInUtc() {}

    /**
     * Sample output: 2021-11-03T22:52:57Z Reference:
     * https://stackoverflow.com/a/19632076/6323360
     */
    public static String getCurrentTimeInUtc() {
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        final String timeString = now.format(DATE_TIME_FORMATTER);
        return timeString;
    }
}
