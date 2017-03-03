package com.tony.springcloud.integration;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.tony.springcloud.client.CustomerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Hystrix组件, 包裹customer服务
 * <p>
 * Created by Tony on 02/03/2017.
 */
@Component
public class CustomerIntegration {

    private final CustomerClient customerClient;
    private Random random = new Random();

    @Autowired
    public CustomerIntegration(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    @HystrixCommand(fallbackMethod = "defaultCustomer",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
            })
    public String getCustomer(String message) {
        if (random.nextInt(10) < 8)
            return customerClient.test() + " " + message;
        else
            throw new RuntimeException("Mock network error.");
    }

    public String defaultCustomer(String message) {
        return "I am the default customer Hib";
    }

}
