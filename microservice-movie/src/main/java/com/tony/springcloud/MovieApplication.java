package com.tony.springcloud;

import com.tony.springcloud.config.CustomerRandomBalance;
import com.tony.springcloud.config.ExcludeFromComponentScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
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
@RibbonClient(name = "microservice-customer", configuration = CustomerRandomBalance.class)
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ExcludeFromComponentScan.class)})
public class MovieApplication {

    private final RestTemplate restTemplate;

    private final LoadBalancerClient loadBalancerClient;

    @Autowired
    public MovieApplication(RestTemplate restTemplate, LoadBalancerClient loadBalancerClient) {
        this.restTemplate = restTemplate;
        this.loadBalancerClient = loadBalancerClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }

    @GetMapping("test")
    public String test() {
        String message = restTemplate.getForObject("http://microservice-customer/test", String.class);
        return "Hello movie! The message from customer is -> " + message;
    }

    /**
     * Ribbon负载均衡测试
     *
     * @return
     */
    @GetMapping("call")
    public String loadBalancer() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("microservice-customer");
        ServiceInstance serviceInstance2 = loadBalancerClient.choose("microservice-customer2");
        System.out.println(String.format("%s:%s:%s", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort()));
        System.out.println(String.format("%s:%s:%s", serviceInstance2.getServiceId(), serviceInstance2.getHost(), serviceInstance2.getPort()));
        return "success";
    }
}
