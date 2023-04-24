package com.poc.pubsub.controller;

import com.poc.pubsub.config.publisher.ConsumerProxyFactory;
import com.poc.pubsub.producer.MyOwnPublisherProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/publish")
public class PublisherController {

    @Autowired
    private PubSubTemplate pubSubTemplate;

    @Autowired
    private MyOwnPublisherProducer myOwnPublisherProducer;

    @PostMapping
    HttpStatus publishMessage(@RequestParam String message) {
        try {
            MyOwnPublisherProducer proxy = ConsumerProxyFactory.createProxy(myOwnPublisherProducer);
            proxy.producer(message);
            return HttpStatus.ACCEPTED;
        } catch (Exception ex) {
            log.error("An exception occurred: {}", ex.getMessage());
            return HttpStatus.BAD_REQUEST;
        }
    }
}
