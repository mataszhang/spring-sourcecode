package com.matas.circleref.auto;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author matas
 * @date 2018/2/6 11:03
 * @email mataszhang@163.com
 */
public class AutoC {
    private AutoA a;

    @Autowired
    public void setA(AutoA a) {
        this.a = a;
    }

    public AutoA getA() {
        return a;
    }
}
