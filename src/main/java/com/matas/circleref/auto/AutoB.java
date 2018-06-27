package com.matas.circleref.auto;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author matas
 * @date 2018/2/6 11:03
 * @email mataszhang@163.com
 */
public class AutoB {
    private AutoC c;

    @Autowired
    public void setC(AutoC c) {
        this.c = c;
    }

    public AutoC getC() {
        return c;
    }
}
