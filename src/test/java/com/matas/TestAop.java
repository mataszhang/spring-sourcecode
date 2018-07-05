package com.matas;

import com.matas.aop.IHello;
import com.matas.aop.TestCglibProxyBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author matas
 * @date 2018/6/30 11:25
 * @email mataszhang@163.com
 */
public class TestAop {

    /**
     * JDK 代理
     *
     * @throws Throwable
     * @see org.springframework.aop.framework.JdkDynamicAopProxy#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])  责任链，aroundTest2 后进先出，所有结果先应用上
     */
    @Test
    public void testJdkProxy() throws Throwable {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-aop.xml");
        IHello bean = ctx.getBean(IHello.class);
        String word = bean.sayHello("jack");
        System.out.println(word);
    }

    /**
     * Cglib 代理
     *
     * @throws Throwable
     * @see TestCglib
     * @see org.springframework.aop.framework.CglibAopProxy#getProxy(java.lang.ClassLoader) 创建cglib代理
     * @see org.springframework.aop.framework.CglibAopProxy#getCallbacks(java.lang.Class)  创建Callback
     * @see org.springframework.aop.framework.CglibAopProxy.ProxyCallbackFilter  Callback过滤
     * @see org.springframework.aop.framework.CglibAopProxy.DynamicAdvisedInterceptor  内部封装了Advice拦截器调用链
     */
    @Test
    public void testCglibProxy() throws Throwable {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-aop.xml");
        TestCglibProxyBean bean = ctx.getBean(TestCglibProxyBean.class);
        String word = bean.sayHello("rose");
        System.out.println(word);
    }
}
