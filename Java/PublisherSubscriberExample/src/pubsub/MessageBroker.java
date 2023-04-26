package PublisherSubscriberExample.src.pubsub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageBroker {
    private HashMap<Topic, List<Subscriber>> subscriberList_;
    private static MessageBroker instance_;

    public static MessageBroker getInstance() {
        if (instance_ == null) instance_ = new MessageBroker();
        return instance_;
    }

    private MessageBroker() {
        subscriberList_ = new HashMap<>();
    }

    public void sendMessage(Topic t, String m) {
        List<Subscriber> subs = subscriberList_.getOrDefault(t, new ArrayList<Subscriber>());
        for (Subscriber s1 : subs) s1.receiveMessage(t, m);
    }

    public void registerSubscriber(Subscriber s, Topic t) {
        List<Subscriber> listOfSubscribers =
                subscriberList_.getOrDefault(t, new ArrayList<Subscriber>());
        listOfSubscribers.add(s);
        subscriberList_.put(t, listOfSubscribers);
    }
}
