package com.tony.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * boot class.
 * Created by Tony on 24/02/2017.
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class MovieApplication {

    private final RestTemplate restTemplate;

    @Autowired
    public MovieApplication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }

    @GetMapping("test")
    public String test() {
        String message = restTemplate.getForObject("http://microservice-customer/test", String.class);
        return "Hello movie! The message from customer is -> " + message;
    }
}
