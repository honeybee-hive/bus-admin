package com.github.bus.example;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableFeignClients
@SpringCloudApplication
public class ExampleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleServiceApplication.class, args);
    }
}
