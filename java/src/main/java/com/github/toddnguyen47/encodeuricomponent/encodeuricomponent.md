# Encode URI Component

Use Guava's library instead.

Reference:
- https://stackoverflow.com/a/31595036/6323360
- https://google.github.io/guava/releases/23.0/api/docs/com/google/common/net/UrlEscapers.html

```java
import com.google.common.net.UrlEscapers;

public void sample() {

    // For query parameters. Paths should use another escaper.
    String encoded = UrlEscapers.urlPathSegmentEscaper().escape("some string/here");
}
```
