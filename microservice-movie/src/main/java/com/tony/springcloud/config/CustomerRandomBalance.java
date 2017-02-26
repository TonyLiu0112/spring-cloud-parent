package com.tony.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by admin on 2017/2/26.
 */
@Configuration
@ExcludeFromComponentScan
public class CustomerRandomBalance {
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
