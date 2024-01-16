package com.example.taskmanagementsystem.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.example.taskmanagementsystem.controllers.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();

        log.info("Method {} with parameters {} will execute", methodName, Arrays.asList(arguments));

        Object returnedByMethod = joinPoint.proceed();

        log.info("Method execute and returned: {}", returnedByMethod);

        return returnedByMethod;
    }
}