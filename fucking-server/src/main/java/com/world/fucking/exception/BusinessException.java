package com.world.fucking.exception;

import com.world.fucking.enums.ResultEnum;
import lombok.Getter;

/**
 * @author heisenberg
 * @since 1.0.0
 */
@Getter
public class BusinessException extends RuntimeException {
    /**
     * 异常状态码信息
     * -- GETTER --
     *  获取状态码
     *
     * @return {@link Integer}

     */
    private final Integer status;


    /**
     * 有参构造
     * @param status 状态码
     */
    public BusinessException(int status) {
        super();
        this.status = status;
    }

    /**
     * 有参构造
     * @param status 状态码
     * @param message 消息
     */
    public BusinessException(int status,String message) {
        super(message);
        this.status = status;
    }

    /**
     * 有参构造
     * @param resultEnum 状态码
     */
    public BusinessException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.status = resultEnum.getCode();
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

    /**
     * 构造器
     * @param status 状态码
     * @param cause 异常
     */
    public BusinessException(int status, Throwable cause) {
        super(cause);
        this.status = status;
    }

}
