package com.tony.springcloud;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * boot class.
 * Created by Tony on 24/02/2017.
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class CustomerApplication {

    private final EurekaClient eurekaClient;

    private final DiscoveryClient discoveryClient;

    private Logger logger = Logger.getLogger(CustomerApplication.class);

    @Autowired
    public CustomerApplication(EurekaClient eurekaClient, DiscoveryClient discoveryClient) {
        this.eurekaClient = eurekaClient;
        this.discoveryClient = discoveryClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

    @GetMapping("test")
    public String test() {
        logger.info("Hahahahahahah");
        return "Hello world! Customer!";
    }

    @GetMapping("eureka-instance")
    public String serviceUrl() {
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("microservice-customer", false);
        return instance.getHomePageUrl();
    }

    @GetMapping("server-instance")
    public ServiceInstance serviceInstance() {
        return discoveryClient.getLocalServiceInstance();
    }

}
