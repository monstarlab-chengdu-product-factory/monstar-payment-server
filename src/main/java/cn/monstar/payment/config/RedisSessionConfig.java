package cn.monstar.payment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by shuai on 2017/11/10.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1500)
public class RedisSessionConfig {
}
