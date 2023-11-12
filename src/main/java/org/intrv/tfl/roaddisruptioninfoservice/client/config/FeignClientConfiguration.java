package org.intrv.tfl.roaddisruptioninfoservice.client.config;

import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;
import org.intrv.tfl.roaddisruptioninfoservice.client.exception.ServiceErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class FeignClientConfiguration {
    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new ServiceErrorDecoder();
    }
}
