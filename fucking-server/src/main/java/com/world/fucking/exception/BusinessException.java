package com.world.fucking.exception;

/**
 * @author Heisenberg

 * @version 1.0
 */
public class BusinessException extends RuntimeException {
    /**
     * 异常状态码信息
     */
    private final Integer status;


    public BusinessException(int status) {
        super();
        this.status = status;
    }

    public BusinessException(int status,String message) {
        super(message);
        this.status = status;
    }

    /**
     * cause清楚的定位到是哪里的错（异常的起源）
     * @param status 状态码
     * @param message 消息内容
     * @param cause 异常起源
     */
    public BusinessException(int status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public BusinessException(int status, Throwable cause) {
        super(cause);
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }
}
