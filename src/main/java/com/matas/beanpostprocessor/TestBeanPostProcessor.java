package com.matas.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author matas
 * @date 2018/2/6 16:39
 * @email mataszhang@163.com
 */
public class TestBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("before " + beanName + " invoke custom init-method");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("after " + beanName + " invoke custom init-method");
        return bean;
    }
}
