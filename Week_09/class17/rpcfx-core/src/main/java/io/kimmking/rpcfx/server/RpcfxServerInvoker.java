package io.kimmking.rpcfx.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.api.RpcfxResponseStatus;
import io.kimmking.rpcfx.exception.RpcfxException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class RpcfxServerInvoker {

    private final RpcfxResolver resolver;

    public RpcfxServerInvoker(RpcfxResolver resolver) {
        this.resolver = resolver;
    }

    public RpcfxResponse invoke(RpcfxRequest request) {
        RpcfxResponse response = new RpcfxResponse();
        String serviceClass = request.getServiceClass();

        try {
            // 作业1：改成泛型和反射
            //Object service = resolver.resolve(serviceClass);//this.applicationContext.getBean(serviceClass);
            Object service = this.getService(serviceClass);

            Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
            Object result = method.invoke(service, request.getParams()); // dubbo, fastjson,
            // 两次json序列化能否合并成一个
            response.setResult(result);
            response.setStatus(RpcfxResponseStatus.OK);
            return response;
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            // 3.Xstream

            // 2.封装一个统一的RpcfxException
            response.setException(new RpcfxException("Service Error", e.getMessage()));
            response.setStatus(RpcfxResponseStatus.ERROR);
            return response;
        }
    }

    private Object getService(String serviceClass) throws ClassNotFoundException {
        Class<?> klass = Class.forName(serviceClass);
        Object service = resolver.resolve(klass);
        if (service == null) {
            return resolver.resolve(serviceClass);
        }
        return service;
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }

}
