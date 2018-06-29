package com.matas.bean;

/**
 * @author matas
 * @date 2018/2/5 17:04
 * @email mataszhang@163.com
 */
public class Person {
    protected String address;

    public Person() {
    }

    public Person(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void printAddress() {
        System.out.println("person's address =>" + address);
    }

}
