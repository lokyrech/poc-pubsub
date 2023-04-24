package com.poc.pubsub.config.publisher.processor;

import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gcp.pubsub.core.PubSubException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerProcessor {
    public void addListener(String subscription, Class<?> messageType, Object bean, String methodName) {
        MessageReceiver messageReceiver = (message, consumer) -> {
            try {
                String payload = message.getData().toStringUtf8();
                if (messageType.isAssignableFrom(String.class)) {
                    bean.getClass().getMethod(methodName, String.class).invoke(bean, payload);
                } else {
                    throw new PubSubException("A mensagem não é uma String");
                }
            } catch (Exception e) {
                consumer.nack();
            } finally {
                consumer.ack();
            }
        };

        Subscriber subscriber = Subscriber.newBuilder(subscription, messageReceiver).build();
        subscriber.startAsync().awaitRunning();
    }
}
