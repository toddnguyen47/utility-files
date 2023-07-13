package com.github.toddnguyen47.mystringutils;

import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public final class MyStringUtils {

    public static final int DEFAULT_MAX_WIDTH = 500;
    public static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+");

    private static final int MIN_WIDTH = 4;

    private MyStringUtils() {}

    /**
     * <div>Remove all whitespace, then return abbreviated string</div>
     *
     * <div>Ref: https://stackoverflow.com/a/5455809/6323360</div>
     *
     * @param str
     * @return
     */
    public static String getAbbrevStringNoWhitespace(final String str) {
        return getAbbrevStringNoWhitespace(str, DEFAULT_MAX_WIDTH);
    }

    public static String getAbbrevStringNoWhitespace(final String str, final int maxWidth) {
        // GUARD
        if (StringUtils.isBlank(str)) {
            return str;
        }
        final String strNoWhitespace = removeAllWhitespace(str);
        final int widthUsed = Math.max(maxWidth, MIN_WIDTH);
        return StringUtils.abbreviate(strNoWhitespace, widthUsed);
    }

    public static String removeAllWhitespace(final String input) {
        return replaceWhitespace(input, "");
    }

    public static String replaceWhitespace(final String input, final String replacement) {
        final String output;
        if (StringUtils.isBlank(input)) {
            output = "";
        } else {
            output = WHITESPACE_PATTERN.matcher(input).replaceAll(replacement);
        }
        return output;
    }
}
