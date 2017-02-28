package com.tony.springcloud.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * Feign client
 * <p>
 * Created by Tony on 27/02/2017.
 */
@FeignClient(value = "microservice-customer", fallback = CustomerClient.CustomerClientFallback.class, fallbackFactory = CustomerClient.CustomerClientFallbackFactory.class)
public interface CustomerClient extends CustomerService {

    /**
     * 通过FeignClient的fallback属性处理失败服务处理
     * 注意：一定需要@Component annotation.
     */
    @Component
    class CustomerClientFallback implements CustomerClient {
        @Override
        public String getTest() {
            return "测试服务不可用!";
        }

        @Override
        public String getMessage() {
            return "消息服务不可用";
        }
    }

    /**
     * 通过FeignClient的FallbackFactory属性处理失败服务处理
     */
    @Component
    class CustomerClientFallbackFactory implements FallbackFactory<CustomerClient> {

        @Override
        public CustomerClient create(Throwable cause) {
            return new CustomerClient() {
                @Override
                public String getTest() {
                    return "测试服务真的不可用!";
                }

                @Override
                public String getMessage() {
                    return "消息服务真的不可用";
                }
            };
        }
    }
}