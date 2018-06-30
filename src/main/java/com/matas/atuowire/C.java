package com.matas.atuowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author matas
 * @date 2018/6/30 10:18
 * @email mataszhang@163.com
 */
@Component
public class C {
    @Autowired
    private D d;

    public D getD() {
        return d;
    }
}
