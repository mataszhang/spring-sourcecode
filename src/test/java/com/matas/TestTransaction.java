package com.matas;

import com.matas.transaction.User;
import com.matas.transaction.UserService;
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
public class TestTransaction {
    ApplicationContext ctx = null;

    @Before
    public void before() {
        ctx = new ClassPathXmlApplicationContext("test-transaction.xml");
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


}
