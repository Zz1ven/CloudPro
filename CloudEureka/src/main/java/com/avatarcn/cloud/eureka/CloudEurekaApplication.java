package com.avatarcn.cloud.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.Filter;

@SpringBootApplication
@EnableEurekaServer
public class CloudEurekaApplication {

	private static Logger logger = LoggerFactory.getLogger(CloudEurekaApplication.class);

	@Bean
	public Filter webRequestLoggingFilter() {
		return new CommonsRequestLoggingFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(CloudEurekaApplication.class, args);
		logger.info("============= CloudEureka Start Success =============");
	}
}
