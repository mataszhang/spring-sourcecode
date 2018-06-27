package com.matas.circleref.auto;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author matas
 * @date 2018/2/6 11:03
 * @email mataszhang@163.com
 */
public class AutoA implements InitializingBean {
    private AutoB b;

    @Autowired
    public void setB(AutoB b) {
        this.b = b;
    }

    public AutoB getB() {
        return b;
    }


    public void initA() {
        System.out.println("AutoA do init");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("AutoA afterPropertiesSet()");
    }
}
