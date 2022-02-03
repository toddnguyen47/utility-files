package mystringutils;

public final class MyStringUtils {

    private static final int DEFAULT_MAX_WIDTH = 500;

    private MyStringUtils() {
    }

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
        final String strNoWhitespace = str.replaceAll("\\s", "");
        return StringUtils.abbreviate(strNoWhitespace, maxWidth);
    }
}
