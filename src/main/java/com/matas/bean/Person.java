package com.matas.bean;

import lombok.*;

/**
 * @author matas
 * @date 2018/2/5 17:04
 * @email mataszhang@163.com
 */
@Data
public class Person {
    protected String address;

    public Person() {
    }

    public Person(String address) {
        this.address = address;
    }

    public void printAddress() {
        System.out.println("person's address =>" + address);
    }

}
