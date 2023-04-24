package com.poc.pubsub.config;


import com.poc.pubsub.annotations.PubSubProducer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class PublisherInterceptor implements MethodInterceptor {
    private final PubSubPublisherConfig publisher;
    private final String topicName;

    public PublisherInterceptor(String topicName) {
        this.publisher = new PubSubPublisherConfig();
        this.topicName = topicName;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object result = methodProxy.invokeSuper(obj, args);
        if (method.isAnnotationPresent(PubSubProducer.class) && result instanceof String) {
            String message = (String) result;
            publisher.publish(topicName, message);
        }
        return result;
    }
}
