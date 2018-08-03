package com.matas.bean;

import lombok.*;

/**
 * @author matas
 * @date 2018/2/5 14:24
 * @email mataszhang@163.com
 */
@Data
public class User extends Person {

    private String name;
    private Integer age;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void printAddress() {
        System.out.println("user's address =>" + address);
    }
}
