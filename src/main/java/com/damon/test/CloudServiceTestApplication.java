package com.damon.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@EnableSwagger2
public class CloudServiceTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudServiceTestApplication.class, args);
    }

}

