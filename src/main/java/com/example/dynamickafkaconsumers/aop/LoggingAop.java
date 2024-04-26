package com.example.dynamickafkaconsumers.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAop {
    @Pointcut("@annotation(AopAnnotation)")
    public void callAtMyServiceAnnotation() { }

    @Before("callAtMyServiceAnnotation()")
    public void beforeCallAt() {
        log.info("I am before call at");
    }

    @After("callAtMyServiceAnnotation()")
    public void afterCallAt() {
        log.info("I am after call at");
    }
}
