package com.matas.aop.introduction;

/**
 * @author matas
 * @date 2018/7/29 13:12
 * @email mataszhang@163.com
 */
public class EatFruit implements IEat {
    @Override
    public void eat(String eatable) {
        System.out.println("eat " + eatable);
    }
}
