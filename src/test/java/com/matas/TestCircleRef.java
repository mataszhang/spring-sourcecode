package com.matas;

import com.matas.circleref.auto.AutoA;
import com.matas.circleref.auto.AutoC;
import com.matas.circleref.setter.SetterA;
import com.matas.circleref.setter.SetterB;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author matas
 * @date 2018/6/30 11:27
 * @email mataszhang@163.com
 */
public class TestCircleRef {

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
