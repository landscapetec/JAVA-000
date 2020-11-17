package com.lsf.hw_0902.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CacheInterfaceProxy implements InvocationHandler {
    private Object realObject;
    private static HashMap<String, Object> cacheHashMap = new LinkedHashMap<>();

    public CacheInterfaceProxy(Object realObject) {
        this.realObject = realObject;
    }

    public void setRealObject(Object realObject) {
        this.realObject = realObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method childClassMethod=realObject.getClass().getMethod(method.getName(),method.getParameterTypes());
        MyCache myCache = childClassMethod.getAnnotation(MyCache.class);
        if (myCache == null) {
            return method.invoke(realObject, args);
        }
        String cacheKey = getCacheKey(myCache.prefixKey(), realObject.getClass(), method, args);
        if (cacheHashMap.containsKey(cacheKey)) {
            CacheData cacheData = (CacheData) cacheHashMap.get(cacheKey);
            if (cacheData.expireTime > System.currentTimeMillis()) {
                System.out.println("缓存返回");
                return cacheData.data;
            } else
                cacheHashMap.remove(cacheKey);
        }

        Object result = method.invoke(realObject, args);

        CacheData cacheData = new CacheData(System.currentTimeMillis() + myCache.expireTime() * 1000,
                result);
        cacheHashMap.put(cacheKey, cacheData);

        return result;
    }

    private String getCacheKey(String cachePrefix, Class<?> classType, Method method, Object[] args) {
        return cachePrefix + "_" + classType.getName() + "_" + method.getName() + "_" + Arrays.hashCode(args);
    }

    public Object createProxy() {
        return Proxy.newProxyInstance(realObject.getClass().getClassLoader(), realObject.getClass().getInterfaces(), this);
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
