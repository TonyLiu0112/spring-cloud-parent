package com.tony.springcloud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.tony.springcloud.integration.CustomerIntegration;
import org.apache.log4j.Logger;
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
import org.springframework.web.client.RestTemplate;

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

    private Logger logger = Logger.getLogger(HystrixApplication.class);

    private final RestTemplate restTemplate;
    private final CustomerIntegration customerIntegration;

    private final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Autowired
    public HystrixApplication(RestTemplate restTemplate, CustomerIntegration customerIntegration) {
        this.restTemplate = restTemplate;
        this.customerIntegration = customerIntegration;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(HystrixApplication.class).web(true).run(args);
    }

    @GetMapping("test/{message}")
    public String doTest(@PathVariable("message") String message) {
        return customerIntegration.getCustomer(message);
    }

    @GetMapping("test/timeout")
    @HystrixCommand(fallbackMethod = "defaultTimeout")
    // , commandProperties = {@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")}
    // 未加此配置 说明直接使用hystrix的thread策略，在新的线程池中完成代理，如果加了此配置，请求直接在request所在的线程上下文中请求
    public String timeoutTest() {
        logger.info("main thread: " + Thread.currentThread().getName());
        return restTemplate.getForObject("http://microservice-customer/server-instance", String.class);
    }

    public String defaultTimeout() {
        logger.info("sub thread: " + Thread.currentThread().getName());
        return "服务超时咯！";
    }
}
