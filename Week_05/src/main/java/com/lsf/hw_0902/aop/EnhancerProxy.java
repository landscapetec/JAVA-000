package com.lsf.hw_0902.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class EnhancerProxy implements MethodInterceptor {
    private Object targetObject;

    public EnhancerProxy(Object targetObject) {
        this.targetObject = targetObject;
    }

    public Object createObject() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObject.getClass());
        enhancer.setCallback(this);
        Object object = enhancer.create();
        return object;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invoke(targetObject, objects);
        after();
        return result;
    }


    private void before() {
        System.out.println("----方法执行-前：增强----");
    }

    private void after() {
        System.out.println("----方法执行-后：增强----");
    }
}
