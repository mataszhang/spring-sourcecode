package com.matas;

import com.matas.atuowire.A;
import com.matas.atuowire.B;
import com.matas.bean.Person;
import com.matas.bean.User;
import com.matas.lookup.LookUpMethodTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;

/**
 * 测试Spring容器
 *
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
    public void defaultListableBeanFactory(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("test-context.xml"));

        int beanDefinitionCount = factory.getBeanDefinitionCount();
        System.out.println(beanDefinitionCount);
    }

    @Test
    public void testGetBean() {
        User user = factory.getBean(User.class);
        System.out.println(user);
        System.out.println(user.getClass().getClassLoader());
    }

    @Test
    public void testNameGetBean() {
        User user = (User) factory.getBean("user");
        System.out.println(user);
        System.out.println(user.getClass().getClassLoader());
    }

    @Test
    public void testAliasGet() {
        System.out.println(factory.getBean("p1"));
        System.out.println(factory.getBean("p1"));
    }

    @Test
    public void testBeanFacotry() {
        Object factoryBean = factory.getBean("&userFactory");
        System.out.println(factoryBean);
        Object user = factory.getBean("userFactory");
        System.out.println(user);
        user = factory.getBean("userFactory");
        System.out.println(user);
    }


    @Test
    public void testLookupMethod() {
        LookUpMethodTest bean = factory.getBean(LookUpMethodTest.class);
        Person person = bean.getPerson();
        System.out.println(person);
    }


    @Test
    public void testContructorWithName() throws Throwable {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("test-constructor-with-name.xml");
        User bean = classPathXmlApplicationContext.getBean(User.class);
        System.out.println(bean.getName() + "," + bean.getAge());
    }

    /**
     *解析函数的参数名
     *
     * @param
     * @return
     * @author matas
     * @date 2018/6/30 11:26
     */
    @Test
    public void testParameterNameDiscoverer() {
        LocalVariableTableParameterNameDiscoverer lv = new LocalVariableTableParameterNameDiscoverer();
        for (Constructor c : User.class.getConstructors()) {
            String[] parameterNames = lv.getParameterNames(c);
            String params = StringUtils.arrayToDelimitedString(parameterNames, ",");
            System.out.println(c + "=>" + params);
        }
    }

    @Test
    public void testAutowire() throws Throwable {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("test-autowire.xml");
        A a = classPathXmlApplicationContext.getBean(A.class);
        B b = classPathXmlApplicationContext.getBean(B.class);
        Assert.assertEquals(a, b.getA());
        Assert.assertEquals(b, a.getB());
    }

}
