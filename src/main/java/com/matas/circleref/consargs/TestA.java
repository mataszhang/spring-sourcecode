package com.matas.circleref.consargs;

/**
 * @author matas
 * @date 2018/2/6 10:23
 * @email mataszhang@163.com
 */
public class TestA {
    private TestB b;

    public TestA(TestB b) {
        this.b = b;
    }
}
