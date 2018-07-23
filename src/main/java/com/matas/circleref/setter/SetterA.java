package com.matas.circleref.setter;

/**
 * @author matas
 * @date 2018/2/6 11:03
 * @email mataszhang@163.com
 */
public class SetterA {
    private SetterB b;

    public void setB(SetterB b) {
        this.b = b;
    }

    public SetterB getB() {
        return b;
    }


}
