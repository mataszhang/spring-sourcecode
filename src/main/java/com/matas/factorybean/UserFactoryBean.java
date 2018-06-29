package com.matas.factorybean;

import com.matas.bean.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author matas
 * @date 2018/6/28 13:23
 * @email mataszhang@163.com
 */
public class UserFactoryBean implements FactoryBean<User> {
    private String info;

    @Override
    public User getObject() throws Exception {
        User user = new User();
        String[] split = info.split("-");
        user.setName(split[0]);
        user.setAge(Integer.parseInt(split[1]));
        user.setAddress(split[2]);
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
