package com.poc.pubsub.producer;

import io.micronaut.gcp.pubsub.annotation.PubSubClient;
import io.micronaut.gcp.pubsub.annotation.Topic;

@PubSubClient
public interface PublisherProducer {

    @Topic("my-topic")
    void send(byte[] data);
}
