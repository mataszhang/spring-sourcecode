package com.matas.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author matas
 * @date 2018/7/3 17:20
 * @email mataszhang@163.com
 */
public class TestEvent extends ApplicationEvent {
    private String msage;

    public TestEvent(Object source) {
        super(source);
    }

    public TestEvent(Object source, String msage) {
        super(source);
        this.msage = msage;
    }

    public void print() {
        System.out.println(msage);
    }
}
