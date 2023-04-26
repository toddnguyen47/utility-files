package PublisherSubscriberExample.src.pubsub;

import java.util.List;

public class Subscriber {
    private List<Topic> topics_;

    public Subscriber(List<Topic> topics) {
        topics_ = topics;
    }

    public void subscribeToAllTopics() {
        for (Topic t : topics_) {
            MessageBroker.getInstance().registerSubscriber(this, t);
        }
    }

    public void receiveMessage(Topic t, String m) {
        System.out.println("Subscriber receiveMessage");
        System.out.println(t.getTopicName());
        System.out.println(m);
        System.out.println("-----");
    }
}
