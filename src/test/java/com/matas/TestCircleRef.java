package com.matas;

import com.matas.circleref.auto.AutoA;
import com.matas.circleref.auto.AutoC;
import com.matas.circleref.setter.SetterA;
import com.matas.circleref.setter.SetterB;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author matas
 * @date 2018/6/30 11:27
 * @email mataszhang@163.com
 */
public class TestCircleRef {

    @Test
    public void testBind() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.setAllowCircularReferences(true);
        RootBeanDefinition a = new RootBeanDefinition(SetterA.class);
        RootBeanDefinition b = new RootBeanDefinition(SetterB.class);

        beanFactory.registerBeanDefinition("a", a);
        beanFactory.registerBeanDefinition("b", b);

        //设置属性
        a.getPropertyValues().addPropertyValue("b", new RuntimeBeanReference("b"));
        b.getPropertyValues().addPropertyValue("a", new RuntimeBeanReference("a"));


        SetterA a1 = beanFactory.getBean("a", SetterA.class);
        SetterB b1 = beanFactory.getBean("b", SetterB.class);

        System.out.println(a1.getB().equals(b1));
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
        Assert.assertEquals(a.getB(), b);
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
}
