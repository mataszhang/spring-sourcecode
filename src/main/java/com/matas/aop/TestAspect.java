package com.matas.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author matas
 * @date 2018/7/4 10:29
 * @email mataszhang@163.com
 */
@Aspect
public class TestAspect {
    @Pointcut("execution(* *.sayHello(..))")
    public void point() {
    }

    @Before("execution(* *.sayHello(..))")
    public void beforeTest() {
        System.err.println("before");
    }

    @After("execution(* *.sayHello(..))")
    public void afterTest() {
        System.err.println("after");
    }

    @Around("point()")
    public Object aroundTest(ProceedingJoinPoint p) {
        System.err.println("around before");
        Object o = null;
        try {
            o = p.proceed();
            o  = o +" around";
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.err.println("around after");
        return o;
    }



}
