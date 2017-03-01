package com.tony.springcloud.feign;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 客户服务
 * Created by Tony on 28/02/2017.
 */
public interface CustomerService {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    String getTest();

    @RequestMapping(value = "message", method = RequestMethod.GET)
    String getMessage();

}
