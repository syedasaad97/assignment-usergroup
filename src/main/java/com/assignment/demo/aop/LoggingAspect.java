package com.assignment.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Around("within(@org.springframework.stereotype.Repository *) " +
            " || within(com.assignment.demo.controller.*) " +
            " || within(com.assignment.demo.service.*)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Enter: {}.{}()", joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        log.info("Exit: {}.{}() with result = {}", joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName(), result);
        return result;
    }
}
