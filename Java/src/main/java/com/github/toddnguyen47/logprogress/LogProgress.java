package com.github.toddnguyen47.logprogress;

/**
 * Log Progress
 *
 */
public class LogProgress {

    public static String getProgressString(final int current, final int maxProgress) {
        // GUARD
        if (maxProgress <= 0) {
            return "MAX PROGRESS IS ZERO";
        }

        final double percentage = (current / (double) maxProgress) * 100.0;
        return String.format("PROCESSING: %d/%d, %.2f%%", current, maxProgress, percentage);
    }
}
