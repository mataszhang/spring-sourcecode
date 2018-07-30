package com.matas;

import com.matas.aop.IHello;
import com.matas.aop.SayHelloBean;
import com.matas.aop.xml.TestAdvice;
import com.matas.aop.xml.TestAdvisor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.SpringProxy;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.*;
import org.springframework.aop.framework.adapter.DefaultAdvisorAdapterRegistry;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.target.HotSwappableTargetSource;
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
        SayHelloBean target = new SayHelloBean();
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
        /**
         * @see AopProxyUtils#completeProxiedInterfaces(org.springframework.aop.framework.AdvisedSupport)
         */
        Advised advised = (Advised) bean;
        Advisor[] advisors = advised.getAdvisors();
        boolean frozen = advised.isFrozen();
        Class[] proxiedInterfaces = advised.getProxiedInterfaces();
        TargetSource targetSource = advised.getTargetSource();

        advised.addAdvice(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.err.println("创建proxy后添加的Advice。org.springframework.aop.framework.ProxyConfig.frozen属性必须为false");
                return invocation.proceed();
            }
        });


        SpringProxy sp = (SpringProxy) bean;

        String word = bean.sayHello("jack");
        System.err.println(word);

        System.err.println("======================================");
        IHello bean2 = (IHello) ctx.getBean("testAop");
        bean2.sayHello("jack2");
    }

    /**
     * 测试HotSwappableTargetSource
     *
     * @param
     * @return
     * @author matas
     * @date 2018/7/23 16:13
     */
    @Test
    public void testHotSwap() throws Throwable {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-aop-xml.xml");
        IHello bean = ctx.getBean("testHotSwap", IHello.class);
        bean.sayHello("jack");

        System.err.println("**********************动态切换 target*************************");
        HotSwappableTargetSource hotSwappableTargetSource = ctx.getBean("hotSwappableTargetSource", HotSwappableTargetSource.class);
        hotSwappableTargetSource.swap(ctx.getBean("otherHelloBean", IHello.class));

        bean.sayHello("jack");
    }


    @Test
    public void testBeanNameAutoProxyCreator() {
        Logger.getRootLogger().setLevel(Level.WARN);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-aop-autoproxy.xml");
        IHello sayHelloBean = ctx.getBean("sayHelloBean", IHello.class);
        sayHelloBean.sayHello("jack");
    }


}
