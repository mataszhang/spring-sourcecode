package com.matas;

import com.matas.atuowire.A;
import com.matas.atuowire.C;
import com.matas.atuowire.D;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

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

    @Test
    public void testAutowired(){
        C c = context.getBean(C.class);
        D d = context.getBean(D.class);
        Assert.assertEquals(c, d.getC());
    }

    /**
     * @see AbstractApplicationContext#registerBeanPostProcessors(ConfigurableListableBeanFactory)  ApplicationContext 已经将所有的容器中的BeanPostProcessor注册
     *
     * @param
     * @return
     * @author matas
     * @date 2018/6/30 11:42
     */
    @Test
    public void testAllBeanPostProcessor(){
        System.out.println("=============Spring中已加载的所有的BeanPostProcessor==========");
        Map<String, BeanPostProcessor> beansOfType = context.getBeansOfType(BeanPostProcessor.class);
        for (String key : beansOfType.keySet()) {
            System.out.println(key + "=>" + beansOfType.get(key));
        }
        System.out.println("=============Spring中已加载的所有的BeanPostProcessor==========");
    }

    @Test
    public void testAutowireBeanFactory(){
        C c = context.getBean(C.class);
        System.out.println(c.getBeanFactory());
    }

}
