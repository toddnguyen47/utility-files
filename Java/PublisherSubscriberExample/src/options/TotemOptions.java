package PublisherSubscriberExample.src.options;

import java.util.HashMap;
import java.util.Map;
import pubsub.MessageBroker;
import pubsub.Topic;

public class TotemOptions implements IOptions {
    public static final Topic TOTEM_OPTIONS_TOPIC = new Topic("TOTEM_OPTIONS");

    @Override
    public Map<String, Map<String, String>> getOptionTable() {
        Map<String, Map<String, String>> options = new HashMap<>();
        return options;
    }

    @Override
    public void setOption(String option, String value) {
        System.out.println(option + ": " + value);
        MessageBroker.getInstance().sendMessage(TOTEM_OPTIONS_TOPIC, "SET_OPTION");
    }
}
