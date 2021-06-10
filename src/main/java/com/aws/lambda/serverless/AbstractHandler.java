package com.aws.lambda.serverless;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractHandler<T> {
    private ApplicationContext applicationContext;

    protected String exampleEvent = "{}";

    public AbstractHandler() {
        Class typeParameterClass = ((Class) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0]);

        if (!typeParameterClass.isAnnotationPresent(Configuration.class)) {
            throw new RuntimeException(typeParameterClass + " is not a @Configuration class");
        }
        applicationContext = new AnnotationConfigApplicationContext(typeParameterClass);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public String getExampleEvent() {
        return exampleEvent;
    }
}
