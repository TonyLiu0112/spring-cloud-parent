package com.tony.springcloud.feign;

import com.tony.config.Customer2Config;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 使用feign默认契约完成jax-rs请求
 * <p>
 * Created by Tony on 28/02/2017.
 */
@FeignClient(value = "microservice-customer2", configuration = Customer2Config.class)
public interface Customer2Client {

    @RequestLine("GET /test")
    String test();

}
