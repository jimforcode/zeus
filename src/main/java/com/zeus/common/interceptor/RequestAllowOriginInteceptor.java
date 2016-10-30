package com.zeus.common.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RequestAllowOriginInteceptor {
    @Pointcut("execution(* com.zeus.controller.CpuMonitorController.*(..))")
    private void request() {
    }

    @Before("request()")
    public void doAccessCheck(JoinPoint joint) {
        System.out.println(joint);
        System.out.println("前置通知");
    }

}
