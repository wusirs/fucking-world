package com.world.fucking.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis
 *
 * @author heisenberg
 */
@Configuration
public class RedisConfig {

    /**
     * redis
     *
     * @return LettuceConnectionFactory
     */
    @Bean
    LettuceConnectionFactory redisConnectionFactory() {
        // 连接 redis 配置
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setPassword(""); // 未设置密码也要配置为空 否则抛出 NO AUTH 异常
        return new LettuceConnectionFactory(configuration);
    }

    /**
     * 序列化规则定义
     *
     * @param factory f
     * @return {@link RedisTemplate}
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        //使用 jackson 进行序列化
        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer =
                new Jackson2JsonRedisSerializer<>(Object.class);
        //规定序列化规则
        ObjectMapper objectMapper = new ObjectMapper();
        /*
         * 第一个参数指的是序列化的域，ALL指的是字段、get和set方法、构造方法
         * 第二个参数指的是序列化哪些访问修饰符，默认是public，ANY指任何访问修饰符
         */
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //指定序列化输入的类型，类必须是非final修饰的类
        jsonRedisSerializer.setObjectMapper(objectMapper);
        //序列化key value
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
