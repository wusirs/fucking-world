package com.heisenberg.blbl;

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
public class BLBLApplication {
    public static void main(String[] args) {
        SpringApplication.run(BLBLApplication.class, args);
    }
}
