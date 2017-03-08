package com.tony.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class RsaClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(RsaClientApplication.class, args);
    }

    @Value("${haha.user.password}")
    public String password;

    @GetMapping("getProperties")
    public String getPassword() {
        return password;
    }
}
