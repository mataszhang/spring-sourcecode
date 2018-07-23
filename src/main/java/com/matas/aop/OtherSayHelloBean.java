package com.matas.aop;

/**
 * @author matas
 * @date 2018/7/4 10:28
 * @email mataszhang@163.com
 */
public class OtherSayHelloBean implements IHello {
    @Override
    public String sayHello(String name) {
        System.err.println("invoke other sayHello");
        return "hello other " + name;
    }
}
