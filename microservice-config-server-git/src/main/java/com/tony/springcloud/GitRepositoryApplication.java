package com.tony.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class GitRepositoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(GitRepositoryApplication.class, args);
    }

}
