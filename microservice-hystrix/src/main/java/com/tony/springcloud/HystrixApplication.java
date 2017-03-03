package com.tony.springcloud;

import com.tony.springcloud.integration.CustomerIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@RestController
@EnableFeignClients
public class HystrixApplication {

    private final CustomerIntegration customerIntegration;

    @Autowired
    public HystrixApplication(CustomerIntegration customerIntegration) {
        this.customerIntegration = customerIntegration;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(HystrixApplication.class).web(true).run(args);
    }

    @GetMapping("test/{message}")
    public String doTest(@PathVariable("message") String message) {
        return customerIntegration.getCustomer(message);
    }

}
