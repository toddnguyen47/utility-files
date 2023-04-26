package PublisherSubscriberExample.src.options;

import java.util.Map;

public interface IOptions {
    public Map<String, Map<String, String>> getOptionTable();

    public void setOption(String option, String value);
}
