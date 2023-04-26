package PublisherSubscriberExample.src.pubsub;

public class Topic {
    private String nameOfTopic_;

    public Topic(String nameOfTopic) {
        nameOfTopic_ = nameOfTopic;
    }

    public String getTopicName() {
        return nameOfTopic_;
    }
}
