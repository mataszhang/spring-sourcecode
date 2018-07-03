package com.matas;

import com.matas.atuowire.C;
import com.matas.atuowire.D;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.core.io.ClassPathResource;

import java.util.Map;

/**
 * @author matas
 * @date 2018/6/30 11:25
 * @email mataszhang@163.com
 */
public class TestAutowiredAnnotation {

    /**
     * XmlBeanFactory的   {@link org.springframework.beans.factory.annotation.Autowired}
     *
     * @throws Throwable
     * @see AbstractAutowireCapableBeanFactory#populateBean(String, RootBeanDefinition, BeanWrapper)  1111行 的hasInstAwareBpps
     * @see AbstractAutowireCapableBeanFactory#initializeBean(String, Object, RootBeanDefinition)  1479行
     * @see AnnotationConfigUtils#registerAnnotationConfigProcessors(BeanDefinitionRegistry, java.lang.Object)  debug调用栈，在处理自定义标签是加入到spring
     */
    @Test
    public void testAutowiredAnnoctation() throws Throwable {
        XmlBeanFactory xbf = new XmlBeanFactory(new ClassPathResource("test-autowire-annotation.xml"));
        //AutowiredAnnotationBeanPostProcessor注册到spring中
        xbf.addBeanPostProcessor(xbf.getBean(AutowiredAnnotationBeanPostProcessor.class));
        // xbf.addBeanPostProcessor(xbf.getBean(TestBeanPostProcessor.class));
        System.out.println("=============Spring中已加载的所有的BeanPostProcessor==========");
        Map<String, BeanPostProcessor> beansOfType = xbf.getBeansOfType(BeanPostProcessor.class);
        for (String key : beansOfType.keySet()) {
            System.out.println(key + "=>" + beansOfType.get(key));
        }
        System.out.println("=============Spring中已加载的所有的BeanPostProcessor==========");

        C c = xbf.getBean(C.class);
        D d = xbf.getBean(D.class);
        Assert.assertEquals(c, d.getC());
    }
}
