package com.world.fucking.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 缓存预热
 * @author heisenberg
 * @since 1.0.0
 */
@Component
public class RedisCacheWarmer implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 执行缓存预热操作
    }
}
