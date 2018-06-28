package com.matas.customtag;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author matas
 * @date 2018/6/28 9:41
 * @email mataszhang@163.com
 */
public class UserNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
