package com.world.fucking.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * redis
 * @author heisenberg
 */
@Configuration
public class RedisConfig {

    /**
     * redis
     * @return LettuceConnectionFactory
     */
    @Bean
    LettuceConnectionFactory redisConnectionFactory() {
        // 连接 redis 配置
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setPassword(""); // 未设置密码也要配置为空 否则抛出 NO AUTH 异常
        return new LettuceConnectionFactory(configuration);
    }
}
