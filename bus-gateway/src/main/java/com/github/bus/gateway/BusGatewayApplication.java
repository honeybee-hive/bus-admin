package com.github.bus.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
public class BusGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusGatewayApplication.class, args);
    }
}
