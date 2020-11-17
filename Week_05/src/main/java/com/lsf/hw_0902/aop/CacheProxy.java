package com.lsf.hw_0902.aop;

import org.checkerframework.checker.units.qual.C;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CacheProxy implements MethodInterceptor {
    private Object realObject;
    private static HashMap<String, Object> cacheHashMap = new LinkedHashMap<>();

    public CacheProxy(Object realObject) {
        this.realObject = realObject;
    }

    public void setRealObject(Object realObject) {
        this.realObject = realObject;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        MyCache myCache = method.getAnnotation(MyCache.class);
        if (myCache == null) {
            return method.invoke(realObject, objects);
        }
        String cacheKey = getCacheKey(myCache.prefixKey(), realObject.getClass(), method, objects);
        if (cacheHashMap.containsKey(cacheKey)) {
            CacheData cacheData = (CacheData) cacheHashMap.get(cacheKey);
            if (cacheData.expireTime > System.currentTimeMillis()) {
                System.out.println("缓存返回");
                return cacheData.data;
            } else
                cacheHashMap.remove(cacheKey);
        }

        Object result = method.invoke(realObject, objects);

        CacheData cacheData = new CacheData(System.currentTimeMillis() + myCache.expireTime() * 1000,
                result);
        cacheHashMap.put(cacheKey, cacheData);

        return result;
    }

    private String getCacheKey(String cachePrefix, Class<?> classType, Method method, Object[] args) {
        return cachePrefix + "_" + classType.getName() + "_" + method.getName() + "_" + Arrays.hashCode(args);
    }

    public Object createProxy() {
        //return Proxy.newProxyInstance(realObject.getClass().getClassLoader(), realObject.getClass().getInterfaces(), this);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(realObject.getClass());
        enhancer.setCallback(this);
        Object object = enhancer.create();
        return object;
    }


    class CacheData {
        private long expireTime;
        private Object data;

        CacheData(long expireTime, Object data) {
            this.expireTime = expireTime;
            this.data = data;
        }

        public long getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(long expireTime) {
            this.expireTime = expireTime;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}
