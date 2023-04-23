package com.poc.pubsub.controller;

import com.poc.pubsub.producer.MyOwnPublisherProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publish")
public class PublisherController {

    @Autowired
    private PubSubTemplate pubSubTemplate;

    @Autowired
    private MyOwnPublisherProducer myOwnPublisherProducer;

    @PostMapping
    HttpStatus publishMessage(@RequestParam String message) {
        myOwnPublisherProducer.producer(message);

//        pubSubTemplate.publish("my-topic", message);
//
        return HttpStatus.ACCEPTED;
    }
}
