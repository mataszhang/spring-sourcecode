package com.matas.lookup;

import com.matas.bean.Person;

/**
 * @author matas
 * @date 2018/2/5 17:25
 * @email mataszhang@163.com
 */
public abstract class LookUpMethodTest {
    public void test() {
        this.getPerson().printAddress();
    }

    public abstract Person getPerson();
}
