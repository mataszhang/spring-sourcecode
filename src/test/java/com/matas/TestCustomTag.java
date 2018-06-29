package com.matas;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * 测试Spring容器
 *
 * @author matas
 * @date 2018/2/5 14:17
 * @email mataszhang@163.com
 */
public class TestCustomTag {

    private BeanFactory factory;

    @Before
    public void before() {
        factory = new XmlBeanFactory(new ClassPathResource("test-customtag.xml"));
    }

    @Test
    public void testGetBean() {
        Object test_user = factory.getBean("test_user");
        System.out.println(test_user);
    }

}
