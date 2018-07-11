package com.matas.aop.xml;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

/**
 * 使用NameMatchMethodPointcut将匹配say开头的方法
 *
 * @author matas
 * @date 2018/7/10 15:41
 * @email mataszhang@163.com
 */
public class TestAdvisor extends AbstractPointcutAdvisor {
    private Advice advice;

    @Override
    public Pointcut getPointcut() {
        NameMatchMethodPointcut namePointcut = new NameMatchMethodPointcut();
        namePointcut.setMappedName("say*");
        return namePointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
