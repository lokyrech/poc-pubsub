package com.poc.pubsub.consumer;

import com.poc.pubsub.annotations.PubSubConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyOwnSubscriptionConsumer {

    @PubSubConsumer(subscription = "projects/pubsub-384323/subscriptions/blaster-sub", messageType = String.class)
    public void consumer(String message) {
        log.info(message);
    }
}
