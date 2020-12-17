package io.kimmking.rpcfx.server;

public interface RpcfxResolver {

    Object resolve(String serviceClass);

    Object resolve(Class<?> type);

}
