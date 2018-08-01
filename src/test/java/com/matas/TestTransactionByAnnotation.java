package com.matas;

import com.matas.transaction.User;
import com.matas.transaction.UserService;
import com.matas.transaction.nested.NestedServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author matas
 * @date 2018/7/5 10:33
 * @email mataszhang@163.com
 */
public class TestTransactionByAnnotation {
    ApplicationContext ctx = null;

    @Before
    public void before() {
        ctx = new ClassPathXmlApplicationContext("test-transaction-annotation.xml");
    }

    @Test
    public void testInsert() {
        UserService userService = ctx.getBean(UserService.class);
        User user = new User();
        user.setName("a");
        user.setAge(1);
        user.setSex("male");
        userService.save(user);
    }

    @Test
    public void testList() {
        UserService userService = ctx.getBean(UserService.class);
        List<User> users = userService.getUsers();
        System.out.println(users);
    }

    /**
     * 测试PROPAGATION_NESTED
     *
     * @param
     * @return
     * @author matas
     * @date 2018/8/1 16:57
     */
    @Test
    public void testNested() {
        Logger.getRootLogger().setLevel(Level.WARN);

        NestedServiceImpl nestedService = ctx.getBean(NestedServiceImpl.class);
        User user = new User();
        user.setName("a");
        user.setAge(1);
        user.setSex("male");
        nestedService.save(user);
    }


}
