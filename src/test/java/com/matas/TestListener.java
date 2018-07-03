package com.matas;

import com.matas.event.TestEvent;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author matas
 * @date 2018/6/30 11:25
 * @email mataszhang@163.com
 */
public class TestListener {
    @Test
    public void testAutowiredAnnoctation() throws Throwable {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-event.xml");
        TestEvent event = new TestEvent(ctx,"123");
        ctx.publishEvent(event);
        event = new TestEvent("1","123");
        ctx.publishEvent(event);
    }
}
