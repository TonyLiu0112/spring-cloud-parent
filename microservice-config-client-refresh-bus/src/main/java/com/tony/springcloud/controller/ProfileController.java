package com.tony.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Tony on 09/03/2017.
 */
@RestController
@RefreshScope
public class ProfileController {

    @Value("${haha.user.password}")
    private String properties;

    @GetMapping("getProperties")
    public String getProperties() {
        return properties;
    }
}
