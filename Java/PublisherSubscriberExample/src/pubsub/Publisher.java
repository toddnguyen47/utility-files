package pubsub;

/**
 * Topic-based system.
 * 
 * @author Todd Nguyen
 *
 */
public class Publisher {
  Topic topic_;

  public Publisher(Topic t) {
    topic_ = t;
  }

  public void publish(String m) {
    MessageBroker.getInstance().sendMessage(topic_, m);
  }
}
