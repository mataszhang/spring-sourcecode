package com.matas.aop.xml;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author matas
 * @date 2018/7/10 15:49
 * @email mataszhang@163.com
 */
public class TestAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.err.println("before advice");
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.err.println("afterReturning advice,method " + method.getName() + " return=>" + returnValue);
    }
}
