package testutils;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public final class GetTimeInUtc {
    private GetTimeInUtc() {
    }

    /**
     * Sample output: 2021-11-03T22:52:57Z
     * Reference: https://stackoverflow.com/a/19632076/6323360
     */
    public static String getCurrentTimeInUtc() {
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        final String pattern = "yyyy-MM-dd'T'HH:mm:ssX";
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        final String timeString = now.format(dateTimeFormatter);
        return timeString;
    }
}
