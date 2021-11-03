package testutils;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public final class GetTimeInUtc {
    private GetTimeInUtc() {
    }

    /**
     * Sample output: 2021-11-03T22:52:57Z
     */
    public static String getCurrentTimeInUtc() {
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        final String pattern = "yyyy-MM-dd'T'HH:mm:ssX";
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        final String timeString = now.format(dateTimeFormatter);
        return timeString;
    }
}
