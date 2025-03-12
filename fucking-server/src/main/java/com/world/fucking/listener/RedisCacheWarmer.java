package com.world.fucking.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author Heisenberg
 * @since 1.0.0
 * @date 2024-03-18 18:14:14
 */
@Component
public class RedisCacheWarmer implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 执行缓存预热操作
    }
}
