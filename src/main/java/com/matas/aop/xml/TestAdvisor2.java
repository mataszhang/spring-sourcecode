package com.matas.aop.xml;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * 使用DynamicMethodMatcherPointcut将匹配所有方法
 *
 * @author matas
 * @date 2018/7/10 15:41
 * @email mataszhang@163.com
 */
public class TestAdvisor2 extends AbstractPointcutAdvisor {
    private Advice advice;

    @Override
    public Pointcut getPointcut() {
        DynamicMethodMatcherPointcut dynamicMethodMatcherPointcut = new DynamicMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass, Object[] args) {
                System.err.println("根据参数动态判断的PointCut: method=>" + method.getName());
                if (null != args && null != args[0]) {
                    return "jack".equals(args[0]);
                }
                return false;
            }
        };
        return dynamicMethodMatcherPointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
