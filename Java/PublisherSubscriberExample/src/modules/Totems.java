package PublisherSubscriberExample.src.modules;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import options.TotemOptions;
import pubsub.Subscriber;
import pubsub.Topic;

public class Totems implements IModules {
  private Map<String, Map<String, String>> options_;
  private Subscriber totemsSubscriber_;

  public Totems() {
    totemsSubscriber_ =
        new TotemsSubscriber(Arrays.asList(new Topic[] {TotemOptions.TOTEM_OPTIONS_TOPIC}));
    totemsSubscriber_.subscribeToAllTopics();
  }

  @Override
  /**
   * Totems' refreshConfig()
   */
  public void refreshConfig() {
    System.out.println("refreshConfig() in Totems!");
    if (options_ != null) {
      for (String key : options_.keySet()) System.out.println(key);
    }
  }

  public void setOptionTable(Map<String, Map<String, String>> options) {
    options_ = options;
  }

  private class TotemsSubscriber extends Subscriber {
    public TotemsSubscriber(List<Topic> topics) {
      super(topics);
    }

    @Override
    public void receiveMessage(Topic t, String m) {
      if (t == TotemOptions.TOTEM_OPTIONS_TOPIC) {
        if (m.equalsIgnoreCase("SET_OPTION")) {
          refreshConfig();
        }
      }
    }
  }
}
