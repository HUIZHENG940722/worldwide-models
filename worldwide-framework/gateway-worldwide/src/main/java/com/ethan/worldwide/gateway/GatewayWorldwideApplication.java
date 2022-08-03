package com.ethan.worldwide.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayWorldwideApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayWorldwideApplication.class, args);
    }

}
