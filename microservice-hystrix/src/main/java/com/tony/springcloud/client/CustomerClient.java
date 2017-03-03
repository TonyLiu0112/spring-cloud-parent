package com.tony.springcloud.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Tony on 02/03/2017.
 */
@FeignClient("microservice-customer")
public interface CustomerClient {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    String test();

}
