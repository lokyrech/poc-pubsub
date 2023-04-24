package com.poc.pubsub.config;

import com.poc.pubsub.annotations.PubSubProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cloud.gcp.pubsub.core.PubSubException;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
public class PublishToPubSubProxyFactory {
    public static <T> T createProxy(T target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        var methods = Arrays.stream(target.getClass().getMethods()).filter(method -> method.isAnnotationPresent(PubSubProducer.class)).collect(Collectors.toList());
        if(methods.size() < 1) {
            throw new PubSubException("Topic not valid.");
        }
        var topicName = methods.get(0).getAnnotation(PubSubProducer.class).topic();
        enhancer.setCallback(new PublisherInterceptor(topicName));
        return (T) enhancer.create();
    }
}
