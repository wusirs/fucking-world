package com.world.fucking.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 读取配置文件
 * @author Bella Ciao
 */
@Configuration
@PropertySource(value = "classpath:custom.properties")
public class AppConfig {
}
