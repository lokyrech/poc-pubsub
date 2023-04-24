package com.poc.pubsub.config.consumer;

import com.google.pubsub.v1.ProjectSubscriptionName;
import com.poc.pubsub.annotations.PubSubConsumer;
import com.poc.pubsub.config.consumer.processor.ConsumerProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsumerBeanPostProcessor implements BeanPostProcessor {

    private final ConsumerProcessor consumerProcessor;

    @Override
    public Object postProcessAfterInitialization(Object bean, @NonNull String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(PubSubConsumer.class)) {
                PubSubConsumer annotation = method.getAnnotation(PubSubConsumer.class);
                ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.newBuilder()
                        .setProject(annotation.projectId())
                        .setSubscription(annotation.subscriptionId())
                        .build();
                log.info("The subscription {} has been assigned successfully by class: {}", annotation.subscriptionId(), beanName);
                consumerProcessor.addListener(subscriptionName.toString(), annotation.messageType(), bean, method.getName());
            }
        }
        return bean;
    }
}
