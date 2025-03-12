package com.world.fucking.enums;

/**
 * 定义返回数据结构
 * @author Heisenberg

 * @since 1.0.0
 */
public interface IResult {
    /**
     * 获取状态码
     * @return {@link Integer}
     * @author Heisenberg

     */
    Integer getCode();

    /**
     * 获取消息体
     * @return {@link String}
     * @author Heisenberg

     */
    String getMessage();
}
