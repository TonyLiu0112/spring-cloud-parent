package com.tony.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * 不推荐使用此方式，当指定某一种服务的负载均衡方式之后，如果一不小心，就容易影响别的服务的负载均衡方式，推荐使用配置文件的方式来配置
 * <p>
 * Created by admin on 2017/2/26.
 */
//@Configuration
//@ExcludeFromComponentScan
public class CustomerRandomBalance {
    //    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
