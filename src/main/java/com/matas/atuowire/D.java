package com.matas.atuowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author matas
 * @date 2018/6/30 10:18
 * @email mataszhang@163.com
 */
@Component
public class D {
    @Autowired
    private C c;

    public C getC() {
        return c;
    }
}
