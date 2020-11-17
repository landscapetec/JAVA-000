package com.lsf.hw_0902.aop;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InterfaceProxy implements InvocationHandler {
    private Object realObject;

    public InterfaceProxy(Object realObject) {
        this.realObject = realObject;
    }

    public void setRealObject(Object realObject) {
        this.realObject = realObject;
    }

    private void before() {
        System.out.println("---------方法增强：执行前---------");
    }

    private void after() {
        System.out.println("---------方法增强：执行后---------");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(realObject, args);
        after();
        return result;
    }

    public Object createProxy() {
        return Proxy.newProxyInstance(realObject.getClass().getClassLoader(), realObject.getClass().getInterfaces(), this);
    }
}
