package com.world.fucking;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * start fucking world
 * @author heisenberg
 * @since 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ServletComponentScan
@Slf4j
public class FuckingWorldApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(FuckingWorldApplication.class, args);
        } catch (Exception e) {
            log.error("ApplicationRunner执行失败", e);
            throw new IllegalStateException("启动任务失败", e);
        }
    }
}
