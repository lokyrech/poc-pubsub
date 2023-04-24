package com.poc.pubsub.producer;

import com.poc.pubsub.annotations.PubSubProducer;
import org.springframework.stereotype.Component;

@Component
public class MyOwnPublisherProducer {

    @PubSubProducer(projectId = "pubsub-384323", topicId = "my-topic")
    public String producer(String message) {
        return message;
    }
}
