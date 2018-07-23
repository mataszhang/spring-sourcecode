package com.matas.aop.nested;

import org.springframework.aop.framework.AopContext;

/**
 * @author matas
 * @date 2018/7/23 17:46
 * @email mataszhang@163.com
 */
public class NestInvocationBean {
    public void method1() {
        System.out.println("method1 executed");
        ((NestInvocationBean)AopContext.currentProxy()).method2();
    }

    public void method2() {
        System.out.println("method2 executed");
    }

}
