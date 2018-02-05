package com.matas;

import com.matas.bean.User;
import com.matas.lookup.LookUpMethodTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @author matas
 * @date 2018/2/5 14:17
 * @email mataszhang@163.com
 */
public class TestXmlBeanFactory {
    private BeanFactory factory;

    @Before
    public void before() {
        factory = new XmlBeanFactory(new ClassPathResource("test-context.xml"));
    }

    @Test
    public void testGetBean() {
        User user = factory.getBean(User.class);
        System.out.println(user);
        System.out.println(user.getClass().getClassLoader());
    }

    @Test
    public void testLookupMethod(){
        LookUpMethodTest bean = factory.getBean(LookUpMethodTest.class);
        bean.test();
    }

}
