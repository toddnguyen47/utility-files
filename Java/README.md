# Java

## Joda Date Time ISO 8601 Pattern without milliseconds

```java
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public static final DateTimeFormatter FORMATTER_NO_MILLISECONDS =
			DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZoneUTC();
```
