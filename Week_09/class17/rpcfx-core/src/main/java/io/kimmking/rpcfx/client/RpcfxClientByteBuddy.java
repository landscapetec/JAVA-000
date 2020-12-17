package io.kimmking.rpcfx.client;

import com.alibaba.fastjson.parser.ParserConfig;
import org.assertj.core.internal.bytebuddy.ByteBuddy;
import org.assertj.core.internal.bytebuddy.implementation.InvocationHandlerAdapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class RpcfxClientByteBuddy {

    static {
        ParserConfig.global.addAccept("io.kimmking");
    }

    public static <T> T create(final Class<T> serviceClass, final String url)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) new ByteBuddy()
                .subclass(Object.class)
                .implement(serviceClass)
                .intercept(InvocationHandlerAdapter.of(new RpcfxClientInvocationHandler(serviceClass,url)))
                .make()
                .load(RpcfxClientByteBuddy.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
    }

}
