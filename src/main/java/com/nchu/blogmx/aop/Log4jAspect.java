package com.nchu.blogmx.aop;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Log4jAspect {

    private Logger logger =LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.nchu.blogmx.controller.*.*(..))")
    public void log(){}
}
