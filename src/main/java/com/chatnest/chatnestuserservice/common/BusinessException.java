package com.chatnest.chatnestuserservice.common;

public class BusinessException extends RuntimeException {

    private final int code;

    /**
     * 带错误码的构造函数
     */
    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 默认错误码为 400（Bad Request）
     */
    public BusinessException(String message) {
        this(400, message);
    }

    public int getCode() {
        return code;
    }
}
