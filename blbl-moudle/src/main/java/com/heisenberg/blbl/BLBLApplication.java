package com.heisenberg.blbl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BLBLApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(BLBLApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
