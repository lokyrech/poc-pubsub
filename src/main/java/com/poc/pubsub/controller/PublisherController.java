package com.poc.pubsub.controller;

import com.poc.pubsub.service.PublisherService;
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
    private PublisherService publisherService;

    @GetMapping
    void publishStaticMessage() {
        publisherService.publish("Olá estatico!");
    }

    @PostMapping
    HttpStatus publishMessage(@RequestParam String message) {
        pubSubTemplate.publish("my-topic", message);

        return HttpStatus.ACCEPTED;
    }
}
