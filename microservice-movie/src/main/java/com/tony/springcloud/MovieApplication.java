package com.tony.springcloud;

import com.tony.springcloud.dto.User;
import com.tony.springcloud.feign.Customer2Client;
import com.tony.springcloud.feign.CustomerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * boot class.
 * <p>
 * // 不推荐使用此方式，当指定某一种服务的负载均衡方式之后，如果一不小心，就容易影响别的服务的负载均衡方式，推荐使用配置文件的方式来配置
 * // @RibbonClient(name = "microservice-customer", configuration = CustomerRandomBalance.class)
 * // @ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ExcludeFromComponentScan.class)})
 * <p>
 * Created by Tony on 24/02/2017.
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableFeignClients
public class MovieApplication {

    private final RestTemplate restTemplate;

    private final LoadBalancerClient loadBalancerClient;

    private final CustomerClient customerClient;

    private final Customer2Client customer2Client;

    @Autowired
    public MovieApplication(RestTemplate restTemplate, LoadBalancerClient loadBalancerClient, CustomerClient customerClient, Customer2Client customer2Client) {
        this.restTemplate = restTemplate;
        this.loadBalancerClient = loadBalancerClient;
        this.customerClient = customerClient;
        this.customer2Client = customer2Client;
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }

    @GetMapping("test")
    public String test() {
        String message = restTemplate.getForObject("http://microservice-customer/test", String.class);
        return "Hello movie! The message from customer is -> " + message;
    }

    @GetMapping("testCustomer")
    public String testCustomer() {
        String test = customerClient.getTest();
        String message = customerClient.getMessage();
        return "Hi! Test(" + test + ") Message(" + message + ") get by spring mvc contract!";
    }

    @GetMapping("testCustomer2")
    public String testCustomer2() {
        String test = customer2Client.test();
        return "Hi! Test(" + test + ") get by feign contract!";
    }

    @Value("${movie.user}")
    private String url;

    @GetMapping("getProperties")
    public String getProperties() {
        return url;
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

    @GetMapping("getUser")
    public User getUser(User user) {
        return user;
    }
}
