package com.matas;

import com.matas.aop.IHello;
import com.matas.aop.TestCglibProxyBean;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.config.AopNamespaceHandler;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Element;

import java.lang.reflect.Method;

/**
 * @author matas
 * @date 2018/6/30 11:25
 * @email mataszhang@163.com
 */
public class TestAopByAnnotation {

    /**
     * JDK 代理
     *
     * @throws Throwable
     * @see AopNamespaceHandler 配置aop标签的解析
     * @see BeanDefinitionParserDelegate#parseCustomElement(org.w3c.dom.Element, BeanDefinition)   解析aop自定义标签，注册AutoProxyCreator的BeanDefinition
     * @see AbstractApplicationContext#registerBeanPostProcessors(ConfigurableListableBeanFactory)  获取AutoProxyCreator实例，注册到BeanFactory的BeanPostProcessor中
     * @see org.springframework.aop.config.AspectJAutoProxyBeanDefinitionParser#parse(Element, ParserContext)  将AnnotationAwareAspectJAutoProxyCreator注册到BeanFactory
     * @see AbstractApplicationContext#finishBeanFactoryInitialization(ConfigurableListableBeanFactory) 创建BeanFactory单例对象,则会进入{@link AnnotationAwareAspectJAutoProxyCreator}  {@link SmartInstantiationAwareBeanPostProcessor} 回调中，进而创建代理
     * @see AnnotationAwareAspectJAutoProxyCreator
     * @see AbstractAutoProxyCreator#postProcessBeforeInstantiation(java.lang.Class, java.lang.String) 加载aspect
     * @see AbstractAutoProxyCreator#postProcessAfterInitialization(java.lang.Object, java.lang.String) 创建代理
     * @see org.springframework.aop.framework.JdkDynamicAopProxy#invoke(java.lang.Object, Method, java.lang.Object[])  责任链，aroundTest2 后进先出，所有结果先应用上
     */
    @Test
    public void testJdkProxy() throws Throwable {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-aop-annotation.xml");
        IHello bean = ctx.getBean(IHello.class);
        String word = bean.sayHello("jack");
        System.err.println(word);
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
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-aop-annotation.xml");
        TestCglibProxyBean bean = ctx.getBean(TestCglibProxyBean.class);
        String word = bean.sayHello("rose");
        System.out.println(word);
    }
}
