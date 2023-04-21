package com.poc.pubsub.service;

import com.poc.pubsub.producer.PublisherProducer;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Singleton
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherProducer publisherProducer;

    public void publish(String message) {
        byte[] serializedBody = message.getBytes(StandardCharsets.UTF_8);
        publisherProducer.send(serializedBody);
    }
}

