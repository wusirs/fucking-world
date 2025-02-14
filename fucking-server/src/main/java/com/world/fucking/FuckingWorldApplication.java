package com.world.fucking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * spring boot 启动类
 * @author Ciao
 * @date 2024/08/09
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FuckingWorldApplication {
    public static void main(String[] args) {
        SpringApplication.run(FuckingWorldApplication.class, args);
    }
}
