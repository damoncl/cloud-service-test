package com.damon.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudServiceTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudServiceTestApplication.class, args);
    }

}

