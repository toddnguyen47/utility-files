package com.util;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import com.att.eg.schedule.listeners.currentprogram.util.GetTimeInUtc;

import org.junit.Assert;
import org.junit.Test;

public class GetTimeInUtcTest {
    @Test
    public void test_GivenNow_WhenGettingTime_ThenTimeShouldBeWithinTwoMinInterval() {
        OffsetDateTime now1 = OffsetDateTime.now(ZoneOffset.UTC);
        // Subtract a second to account for milliseconds difference
        now1 = now1.minusSeconds(1);

        final String currentTime = GetTimeInUtc.getCurrentTimeInUtc();

        final OffsetDateTime now2 = now1.plusMinutes(2);
        final OffsetDateTime currentTimeConverted = OffsetDateTime.parse(currentTime, GetTimeInUtc.DATE_TIME_FORMATTER);
        final boolean now1WithinInterval = now1.isBefore(currentTimeConverted) || now1.isEqual(currentTimeConverted);
        final boolean now2WithinInterval = now2.isAfter(currentTimeConverted);
        Assert.assertTrue(
                String.format("now1WithinInterval: %s, now2WithinInterval: %s", now1WithinInterval, now2WithinInterval),
                now1WithinInterval && now2WithinInterval);
    }
}
