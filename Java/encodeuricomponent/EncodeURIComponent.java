package encodeuricomponent;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public final class EncodeURIComponent {
  // Since URLEncoder encodes spaces to `+`, replace `+` with `%20`
  private static final Pattern REGEX_PLUSES = Pattern.compile("\\+");

  private EncodeURIComponent() {}

  public static final String encodeComponent(final String component) {
    return encodeComponentCharset(component, StandardCharsets.UTF_8.toString());
  }

  public static final String encodeComponentCharset(final String component, final String charset) {
    String encoder = "";
    try {
      encoder = URLEncoder.encode(component, charset);
      encoder = REGEX_PLUSES.matcher(encoder).replaceAll("%20");
    } catch (UnsupportedEncodingException e) {
      encoder = component;
    }
    return encoder;
  }
}
