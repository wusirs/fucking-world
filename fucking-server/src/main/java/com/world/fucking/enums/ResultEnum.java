package com.world.fucking.enums;

/**
 *
 * @author heisenberg

 * @since 1.0.0
 */
public enum ResultEnum implements IResult{
    SUCCESS(200, "成功"),
    VALIDATE_FAILED(400, "参数错误"),
    COMMON_FAILED(500, "系统错误"),

    FORBIDDEN(2004, "没有权限访问资源"),
    TIME_EXCEPTION(3001, "时钟回拨，拒绝生成ID")
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
