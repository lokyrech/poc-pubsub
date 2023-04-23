package com.poc.pubsub.config;

import com.poc.pubsub.annotations.PubSubConsumer;
import com.poc.pubsub.processor.PubSubConsumerProcessor;
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
public class PubSubConsumerBeanPostProcessor implements BeanPostProcessor {

    private final PubSubConsumerProcessor pubSubConsumerProcessor;

    @Override
    public Object postProcessAfterInitialization(Object bean, @NonNull String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(PubSubConsumer.class)) {
                PubSubConsumer annotation = method.getAnnotation(PubSubConsumer.class);
                log.info("The subscription {} has been assigned. {}", annotation.subscription(), beanName);
                pubSubConsumerProcessor.addListener(annotation.subscription(), annotation.messageType(), bean, method.getName());
            }
        }
        return bean;
    }
}