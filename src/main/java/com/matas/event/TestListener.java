package com.matas.event;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;

/**
 * @author matas
 * @date 2018/7/3 17:22
 * @email mataszhang@163.com
 */
public class TestListener implements ApplicationListener<TestEvent> , InitializingBean {

    @Override
    public void onApplicationEvent(TestEvent event) {
        event.print();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }
}
