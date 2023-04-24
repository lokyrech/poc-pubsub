package com.poc.pubsub.consumer;

import com.poc.pubsub.annotations.PubSubConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyOwnSubscriptionConsumer {

    @PubSubConsumer(projectId = "pubsub-384323", subscriptionId = "blaster-sub", messageType = String.class)
    public void consumer(String message) {
        log.info(message);
    }
}
