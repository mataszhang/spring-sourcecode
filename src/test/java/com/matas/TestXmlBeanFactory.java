package com.matas;

import com.matas.atuowire.A;
import com.matas.atuowire.B;
import com.matas.bean.User;
import com.matas.circleref.auto.AutoA;
import com.matas.circleref.auto.AutoC;
import com.matas.circleref.setter.SetterA;
import com.matas.circleref.setter.SetterB;
import com.matas.lookup.LookUpMethodTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.beans.factory.BeanFactory;
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
        bean.test();
    }

    /**
     * 通过构造函数注入，循环引用报错。
     *
     * @param
     * @return void
     * @author matas
     * @date 2018/2/6 16:01
     */
    @Test(expected = BeanCurrentlyInCreationException.class)
    public void testCircleRef() throws Throwable {
        try {
            new ClassPathXmlApplicationContext("circle-ref-cons.xml");
        } catch (Exception e) {
            throw e.getCause().getCause().getCause();
        }
    }

    /**
     * 通过property注入
     *
     * @param
     * @return void
     * @author matas
     * @date 2018/2/6 16:00
     */
    @Test
    public void testCircleRef2() throws Throwable {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("circle-ref-settings.xml");
        SetterA a = classPathXmlApplicationContext.getBean(SetterA.class);
        SetterB b = classPathXmlApplicationContext.getBean(SetterB.class);
        Assert.assertEquals(a.getB(),b);
    }

    /**
     * 通过 autowire注入 循环依赖
     *
     * @param
     * @return void
     * @author matas
     * @date 2018/2/6 16:00
     */
    @Test
    public void testCircleRef3() throws Throwable {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("circle-ref-autowire.xml");
        AutoC c = classPathXmlApplicationContext.getBean(AutoC.class);
        AutoA a = classPathXmlApplicationContext.getBean(AutoA.class);

        Assert.assertEquals(c.getClass(), AutoC.class);
        Assert.assertEquals(c.getA(), a);
    }


    @Test
    public void testContructorWithName() throws Throwable {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("test-constructor-with-name.xml");
        User bean = classPathXmlApplicationContext.getBean(User.class);
        System.out.println(bean.getName() + "," + bean.getAge());
    }

    @Test
    public void testParameterNameDiscoverer(){
        LocalVariableTableParameterNameDiscoverer lv = new LocalVariableTableParameterNameDiscoverer();
       for(Constructor c: User.class.getConstructors()){
           String[] parameterNames = lv.getParameterNames(c);
           String params = StringUtils.arrayToDelimitedString(parameterNames, ",");
           System.out.println(c +"=>"+ params);
       }
    }

    @Test
    public void testAutowire() throws Throwable {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("test-autowire.xml");
        A a = classPathXmlApplicationContext.getBean(A.class);
        B b = classPathXmlApplicationContext.getBean(B.class);
        Assert.assertEquals(a ,b.getA());
        Assert.assertEquals(b ,a.getB());
    }

}
