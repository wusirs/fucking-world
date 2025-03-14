package com.world.fucking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * start fucking world
 * @author heisenberg
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FuckingWorldApplication {
    public static void main(String[] args) {
        SpringApplication.run(FuckingWorldApplication.class, args);
    }
}
