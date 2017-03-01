package com.tony.config;

import feign.Contract;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Tony on 28/02/2017.
 */
@Configuration
public class Customer2Config {

    /**
     * 这里使用的是fegin的契约，默认情况下使用的是springmvc的契约
     *
     * @return
     */
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
