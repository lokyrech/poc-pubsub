package com.poc.pubsub.producer;

import com.poc.pubsub.annotations.PubSubProducer;
import org.springframework.stereotype.Component;

@Component
public class MyOwnPublisherProducer {

    @PubSubProducer(topic = "projects/pubsub-384323/topics/my-topic")
    public String producer(String message) {
        return message;
    }
}
