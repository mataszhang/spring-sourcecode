package com.matas;

import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * @author matas
 * @date 2018/7/4 17:10
 * @email mataszhang@163.com
 */
public class TestCglib {

    public static void main(final String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TestCglib.class);
        enhancer.setCallbacks(new Callback[]{new CallBack1(), new CallBack2()});
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                //test方法调用第一个callback,其他调用第二个
                return method.getName().equals("test") ? 0 : 1;
            }
        });

        TestCglib test = (TestCglib) enhancer.create();
        test.test();
        System.out.println(test);
    }

    public void test() {
        System.out.println("TestCglib.test()");
    }


    private static class CallBack1 implements MethodInterceptor {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.err.println("CallBack1=>before invoke " + method);
            Object result = methodProxy.invokeSuper(o, objects);
            System.err.println("CallBack1=>after invoke " + method);
            return result;
        }
    }

    private static class CallBack2 implements MethodInterceptor {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.err.println("CallBack2=>before invoke " + method);
            Object result = methodProxy.invokeSuper(o, objects);
            System.err.println("CallBack2=>after invoke " + method);
            return result;
        }
    }

}
