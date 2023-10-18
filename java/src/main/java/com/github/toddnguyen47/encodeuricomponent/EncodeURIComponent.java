package com.github.toddnguyen47.encodeuricomponent;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public final class EncodeURIComponent {
  // Since URLEncoder encodes spaces to `+`, replace `+` with `%20`
  private static final Pattern REGEX_PLUSES = Pattern.compile("\\+");

  private EncodeURIComponent() {}

  /**
   * encodeUri using Spring's `UriComponentsBuilder`. If no paths or query parameters are needed, simply pass in an
   * empty List or an empty Map, respectively.
   * @param uri
   * @param paths
   * @param queryParams
   * @return
   */
  public static String encodeUri(
      final String uri, final List<String> paths, final Map<String, String> queryParams) {
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(uri);
    for (final String path : paths) {
      uriBuilder = uriBuilder.pathSegment(path);
    }
    for (final Map.Entry<String, String> entry : queryParams.entrySet()) {
      uriBuilder = uriBuilder.queryParam(entry.getKey(), entry.getValue());
    }
    UriComponents uriComponents = uriBuilder.encode().build();
    return uriComponents.toUriString();
  }

  public static String encodeComponent(final String component) {
    return encodeComponentCharset(component, StandardCharsets.UTF_8.toString());
  }

  public static String encodeComponentCharset(final String component, final String charset) {
    String encoder;
    try {
      encoder = URLEncoder.encode(component, charset);
      encoder = REGEX_PLUSES.matcher(encoder).replaceAll("%20");
    } catch (UnsupportedEncodingException e) {
      encoder = component;
    }
    return encoder;
  }
}
