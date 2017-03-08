package com.tony.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
@RestController
public class GitRepositoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(GitRepositoryApplication.class, args);
    }

}
