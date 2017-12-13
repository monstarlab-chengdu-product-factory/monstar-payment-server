package cn.monstar.payment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author wangxianding
 * @version 1.0
 * @description
 * @date 2017/11/28 下午1:36
 */
@Component
@ConfigurationProperties(prefix = "monstar")
@PropertySource("classpath:application-app.yml")
public class MonstarConfig {

    /**
     * 是否启用沙箱环境
     */
    @Value("${sandboxnew}")
    public boolean sandboxnew;

}
