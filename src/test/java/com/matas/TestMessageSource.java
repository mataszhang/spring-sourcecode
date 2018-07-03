package com.matas;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 * @author matas
 * @date 2018/6/30 11:25
 * @email mataszhang@163.com
 */
public class TestMessageSource {


    @Test
    public void testAutowiredAnnoctation() throws Throwable {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-message-source.xml");
        String msg1 = ctx.getMessage("test",null, Locale.US);
        String msg2= ctx.getMessage("test",null, Locale.CHINA);
        System.out.println(msg1);
        System.out.println(msg2);
    }
}
