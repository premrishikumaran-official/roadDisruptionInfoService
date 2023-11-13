package org.intrv.tfl.roaddisruptioninfoservice.client.config;

import feign.Retryer;
import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.intrv.tfl.roaddisruptioninfoservice.client.exception.ServiceErrorDecoder;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;

import java.time.Duration;


public class FeignClientConfiguration {
    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new ServiceErrorDecoder();
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default();
    }

}
