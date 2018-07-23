package com.matas.aop.nested;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;

@Aspect
public class TestNestAspect {
    @Pointcut("execution(* *.method*(..))")
    public void method() {
    }

    @Around("method()")
    public void printTime(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            pjp.proceed();
        } finally {
            stopWatch.stop();
            System.err.println(pjp.getSignature().getName() + "=>" + stopWatch);
        }
    }
}
