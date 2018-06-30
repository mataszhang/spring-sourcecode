package com.matas;

import com.matas.atuowire.A;
import com.matas.bean.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试Spring容器
 *
 * @author matas
 * @date 2018/2/5 14:17
 * @email mataszhang@163.com
 */
public class TestApplicationContext {
    private ApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("test-applicationcontext.xml");
    }

    @Test
    public void testGetBean() {
        A user = context.getBean(A.class);
        System.out.println(user);
        System.out.println(user.getClass().getClassLoader());
    }

}
