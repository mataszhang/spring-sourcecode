package com.matas.aop;

/**
 * @author matas
 * @date 2018/7/4 17:32
 * @email mataszhang@163.com
 */
public class TestCglibProxyBean {

    public String sayHello(String name) {
        return "hello cglib " + name;
    }
}
