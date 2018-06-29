package com.matas.bean;

/**
 * @author matas
 * @date 2018/2/5 14:24
 * @email mataszhang@163.com
 */
public class User extends Person {

    private String name;
    private Integer age;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public void printAddress() {
        System.out.println("user's address =>" + address);
    }
}
