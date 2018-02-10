package com.avatarcn.user.config;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Kenny on 2017/10/31.
 */
@Configuration
public class FeginConfiguration {
    @Bean
    Request.Options feignOptions() {
        return new Request.Options(100 * 1000, 100 * 1000);
    }
    @Bean
    Retryer feignRetryer() {
        return Retryer.NEVER_RETRY;
    }
}
