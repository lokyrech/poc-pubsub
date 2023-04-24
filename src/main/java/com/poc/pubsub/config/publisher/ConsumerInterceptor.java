package com.poc.pubsub.config.publisher;


import com.poc.pubsub.annotations.PubSubProducer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ConsumerInterceptor implements MethodInterceptor {
    private final ConsumerConfig publisher;
    private final String projectId;
    private final String topicName;

    public ConsumerInterceptor(String projectId, String topicName) {
        this.publisher = new ConsumerConfig();
        this.projectId = projectId;
        this.topicName = topicName;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object result = methodProxy.invokeSuper(obj, args);
        if (method.isAnnotationPresent(PubSubProducer.class) && result instanceof String) {
            String message = (String) result;
            publisher.publish(projectId, topicName, message);
        }
        return result;
    }
}
