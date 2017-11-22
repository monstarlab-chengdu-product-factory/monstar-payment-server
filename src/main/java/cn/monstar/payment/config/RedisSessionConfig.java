package cn.monstar.payment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author zhangshuai
 * @version 1.0
 * @description 返回结果API
 * @date 2017/11/22 16:09
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1500)
public class RedisSessionConfig {
}
