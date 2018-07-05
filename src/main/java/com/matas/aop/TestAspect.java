package com.matas.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author matas
 * @date 2018/7/4 10:29
 * @email mataszhang@163.com
 */
@Aspect
public class TestAspect {
    @Pointcut("execution(* *.sayHello(..))")
    public void sayHello() {
    }

    @Before("sayHello()")
    public void beforeTest() {
        System.out.println("before");
    }

    @Before("sayHello()")
    public void afterTest() {
        System.out.println("after");
    }

    @Around("sayHello()")
    public Object aroundTest(ProceedingJoinPoint p) {
        System.out.println("around before");
        Object o = null;
        try {
            o = p.proceed();
            o  = o +" around";
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("around after");
        return o;
    }

    @Around("sayHello()")
    public Object aroundTest2(ProceedingJoinPoint p) {
        System.out.println("around before2");
        Object o = null;
        try {
            o = p.proceed();
            o  = o +" around-2";
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("around after2");
        return o;
    }


}
