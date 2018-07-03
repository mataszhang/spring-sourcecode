package com.matas.atuowire;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author matas
 * @date 2018/6/30 10:18
 * @email mataszhang@163.com
 */
@Component
public class C {
    @Autowired
    private D d;
    @Autowired
    private BeanFactory beanFactory;

    public C() {
        System.out.println("create one C");
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public D getD() {
        return d;
    }
}
