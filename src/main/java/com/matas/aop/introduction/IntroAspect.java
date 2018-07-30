package com.matas.aop.introduction;

import com.matas.aop.SayHelloBean;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * @author matas
 * @date 2018/7/29 13:13
 * @email mataszhang@163.com
 */
@Aspect
public class IntroAspect {
    @DeclareParents(value = "com.matas.aop.SayHelloBean", defaultImpl = EatFruit.class)
    IEat eat;

}
