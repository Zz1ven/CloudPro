package com.avatarcn.test.config;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by z1ven on 2018/2/7 11:50
 */
@Configuration
public class FeignConfiguration {

    @Bean
    Request.Options feignOptions() {
        return new Request.Options(100*1000, 100*1000);
    }

    @Bean
    Retryer feignRetryer() {
        return Retryer.NEVER_RETRY;
    }
}
