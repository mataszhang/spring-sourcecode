package com.matas;

import com.matas.aop.IHello;
import com.matas.aop.TestAopBean;
import com.matas.aop.xml.TestAdvisor;
import com.matas.aop.xml.TestAdvice;
import org.junit.Test;
import org.springframework.aop.framework.DefaultAopProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.adapter.DefaultAdvisorAdapterRegistry;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author matas
 * @date 2018/6/30 11:25
 * @email mataszhang@163.com
 */
public class TestAopByXML {

    /**
     * 使用ProxyFactory，通过编码方式创建Proxy
     *
     * @param
     * @return
     * @author matas
     * @date 2018/7/11 9:
     * @see ProxyFactory
     */
    @Test
    public void testHardCode() {

        TestAdvice testAdvice = new TestAdvice();

        TestAdvisor advisor = new TestAdvisor();
        TestAopBean target = new TestAopBean();
        System.out.println(target);
        ProxyFactory factory = new ProxyFactory();

        factory.setInterfaces(new Class[]{IHello.class});
        factory.setTarget(target);
        factory.addAdvice(testAdvice);
        //factory.addAdvisor(advisor);

        IHello proxy = (IHello) factory.getProxy();
        String val = proxy.sayHello("test.....");
        System.err.println(val);
    }


    /**
     * ProxyFactoryBean 来创建代理
     *
     * @throws Throwable
     * @see AbstractApplicationContext#registerBeanPostProcessors(ConfigurableListableBeanFactory)
     * @see ProxyFactoryBean
     * @see DefaultAopProxyFactory
     * @see DefaultAdvisorAdapterRegistry
     * @see DefaultPointcutAdvisor
     */
    @Test
    public void testJdkProxy() throws Throwable {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-aop-xml.xml");
        IHello bean = (IHello) ctx.getBean("testAop");
        String word = bean.sayHello("jack");
        System.err.println(word);
    }


}
