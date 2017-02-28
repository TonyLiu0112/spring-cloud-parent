package com.tony.springcloud.feign;

import feign.Client;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.cloud.netflix.feign.support.ResponseEntityDecoder;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Import;

/**
 * Created by Tony on 28/02/2017.
 */
@Import(FeignClientsConfiguration.class)
public class FooController {

    private CustomerClient fooClient;

    private CustomerClient adminClient;

    @Autowired
    public FooController(
            ResponseEntityDecoder decoder, SpringEncoder encoder, Client client) {
        this.fooClient = Feign.builder().client(client)
                .encoder(encoder)
                .decoder(decoder)
                .requestInterceptor(new BasicAuthRequestInterceptor("user", "user"))
                .target(CustomerClient.class, "http://microservice-customer");
        this.adminClient = Feign.builder().client(client)
                .encoder(encoder)
                .decoder(decoder)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin", "admin"))
                .target(CustomerClient.class, "http://microservice-customer");
    }
}
