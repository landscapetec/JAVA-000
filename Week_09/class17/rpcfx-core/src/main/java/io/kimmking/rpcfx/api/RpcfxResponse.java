package io.kimmking.rpcfx.api;

import io.kimmking.rpcfx.exception.RpcfxException;

public class RpcfxResponse<T extends Object> {

    private Object result;

    private RpcfxResponseStatus status;

    private RpcfxException exception;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public RpcfxResponseStatus getStatus() {
        return status;
    }

    public void setStatus(RpcfxResponseStatus status) {
        this.status = status;
    }

    public RpcfxException getException() {
        return exception;
    }

    public void setException(RpcfxException exception) {
        this.exception = exception;
    }
}
