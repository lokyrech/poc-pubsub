package com.poc.pubsub.consumer;

import com.google.pubsub.v1.PubsubMessage;
import io.micronaut.gcp.pubsub.annotation.PubSubListener;
import io.micronaut.gcp.pubsub.annotation.Subscription;
import io.micronaut.messaging.Acknowledgement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PubSubListener
public class SubscriptionConsumer {

    @Subscription("projects/pubsub-384323/topics/my-topic")
    private void consumer(PubsubMessage message, Acknowledgement acknowledgement) {
      log.info(message.getData().toStringUtf8());
      acknowledgement.ack();
    }
}
