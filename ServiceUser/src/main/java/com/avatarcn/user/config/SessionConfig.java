package com.avatarcn.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by z1ven on 2017/10/10 11:24
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 600)
public class SessionConfig {

}
