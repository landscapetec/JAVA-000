package io.kimmking.rpcfx.api;

public enum RpcfxResponseStatus {
    OK("200", "成功"),
    VALIDATE_ERROR("404","校验异常"),
    ERROR("500", "服务器异常");

    private String code;
    private String description;

    RpcfxResponseStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
