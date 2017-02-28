package com.tony.springcloud.feign;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Tony on 28/02/2017.
 */
@RestController
@RequestMapping("customer")
public class CustomerResources implements CustomerService{

    @Override
    public String getTest() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
