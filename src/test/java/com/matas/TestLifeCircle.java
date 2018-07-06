package com.matas;

import com.matas.lifecircle.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author matas
 * @date 2018/6/30 11:25
 * @email mataszhang@163.com
 */
public class TestLifeCircle {


    @Test
    public void test() throws Throwable {
        System.err.println("现在开始初始化容器");

        ApplicationContext factory = new ClassPathXmlApplicationContext("test-lifecircle.xml");
        System.err.println("容器初始化成功");
        //得到Preson，并使用
        Person person = factory.getBean("person",Person.class);
        System.err.println(person);
        System.err.println("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext)factory).registerShutdownHook();
    }

}
