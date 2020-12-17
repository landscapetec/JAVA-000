package io.kimmking.rpcfx.exception;

import io.kimmking.rpcfx.api.RpcfxResponseStatus;

import java.io.Serializable;

public class RpcfxException extends RuntimeException {
    private String code;

    @Override
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }

    public RpcfxException() {
        super();
    }

    public RpcfxException(String code, String message) {
        super(message);
        this.code = code;
    }

    public RpcfxException(String message) {
        this(RpcfxResponseStatus.ERROR.getCode(), message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
