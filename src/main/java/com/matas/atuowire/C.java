package com.matas.atuowire;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author matas
 * @date 2018/6/30 10:18
 * @email mataszhang@163.com
 */
@Component
public class C {
    @Autowired
    private D d;


    public C() {
        System.out.println("create one C");
    }

    public D getD() {
        return d;
    }

    @PostConstruct
    public void post(){
        System.out.println("PostConstruct");
    }
}
