package com.world.fucking.common;

/**
 * @author heisenberg
 * @since 1.0.0
 */
public class RedisCommonConst {

    private RedisCommonConst() {
    }

    /**
     * redis city_code key
     */
    public static final String CITY_CODE_PRE_KEY = "cityCode:";

    /**
     * redis city_code key
     */
    public static final String REDIS_COMMON_KEY = "com:fucking:server:common:";

    /**
     * redis lock
     */
    public static final String CITY_CODE_LOCK = "city_code_lock";
}
