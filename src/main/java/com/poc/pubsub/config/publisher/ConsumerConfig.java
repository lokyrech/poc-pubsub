package com.poc.pubsub.config.publisher;

import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@NoArgsConstructor
public class ConsumerConfig {
    public void publish(String topicName, String message) throws Exception {
        Publisher publisher = Publisher.newBuilder(topicName).build();
        ByteString data = ByteString.copyFromUtf8(message);
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();
        publisher.publish(pubsubMessage).get();
        log.info("Published message in topic {}: {}", topicName, message);
        publisher.shutdown();
    }
}
